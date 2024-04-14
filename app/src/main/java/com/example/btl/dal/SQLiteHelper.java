package com.example.btl.dal;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.btl.model.*;


import java.util.ArrayList;
import java.util.List;
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="BTL.db";
    private static int DATABASE_VERSION =1;

    public SQLiteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE users("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "img TEXT,name TEXT,username TEXT, password TEXT, is_admin INTEGER)";
        db.execSQL(sql);
        sql = "CREATE TABLE categorys("+"category_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "category_name TEXT)";
        db.execSQL(sql);
        sql = "CREATE TABLE questions("+"question_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "question_content TEXT,cateid INTEGER,"+"foreign key (cateid) references categorys(category_id))";
        db.execSQL(sql);
        sql = "CREATE TABLE answers("+"answer_id INTEGER PRIMARY KEY AUTOINCREMENT,quesid INTEGER,option1 TEXT," +
                "option2 TEXT,option3 TEXT,option4 TEXT,dapan INTEGER,"+
                "foreign key (quesid) references questions(question_id))";
        db.execSQL(sql);
        sql = "CREATE TABLE previous_results("+"previousrs_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tongdiem INTEGER,"+"user_id INTEGER,"+"cate_id INTEGER,"+"FOREIGN KEY (user_id) REFERENCES users(id)," +
                "FOREIGN KEY (cate_id) REFERENCES categorys(category_id))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    public long addUser(User user){
        ContentValues values = new ContentValues();
        values.put("img",user.getImg());
        values.put("name",user.getName());
        values.put("username",user.getUsername());
        values.put("password",user.getPassword());
        values.put("is_admin",user.getIs_admin());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("users",null,values);
    }
    public User getUserById(int userId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE id = ?", new String[]{String.valueOf(userId)});

        User user = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex("id");
                int imgIndex = cursor.getColumnIndex("img");
                int nameIndex = cursor.getColumnIndex("name");
                int usernameIndex = cursor.getColumnIndex("username");
                int passwordIndex = cursor.getColumnIndex("password");
                int isAdminIndex = cursor.getColumnIndex("is_admin");

                int id = cursor.getInt(idIndex);
                String img = cursor.getString(imgIndex);
                String name = cursor.getString(nameIndex);
                String username = cursor.getString(usernameIndex);
                String password = cursor.getString(passwordIndex);
                int isAdmin = cursor.getInt(isAdminIndex);

                // Tạo đối tượng User
                user = new User(id, img, name, username, password, isAdmin);
            }

            cursor.close();
        }

        return user;
    }
    public long addCategory(Category category){
        ContentValues values = new ContentValues();
        values.put("category_name", category.getCategory_name());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("categorys",null,values);
    }
    public long addQuestion(Question question){
        ContentValues values = new ContentValues();
        values.put("question_content",question.getQuestion_content());
        values.put("cateid",question.getQuestion_cate().getCategory_id());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("questions",null,values);
    }

    public long addAnswer(Answer answer){
        ContentValues values = new ContentValues();
        values.put("quesid", answer.getQuestion().getQuestion_id());
        values.put("option1", answer.getOption1());
        values.put("option2", answer.getOption2());
        values.put("option3", answer.getOption3());
        values.put("option4", answer.getOption4());
        values.put("dapan", answer.getDapan());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("answers",null,values);
    }


    public List<Category> getListCate() {
        List<Category> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM categorys", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int categoryIdIndex = cursor.getColumnIndex("category_id");
                int categoryNameIndex = cursor.getColumnIndex("category_name");

                do {
                    int categoryId = cursor.getInt(categoryIdIndex);
                    String categoryName = cursor.getString(categoryNameIndex);
                    Category category = new Category(categoryId, categoryName);
                    list.add(category);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        return list;
    }

    public Category getCategoryById(int categoryId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM categorys WHERE category_id = ?", new String[]{String.valueOf(categoryId)});

        Category category = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int categoryNameIndex = cursor.getColumnIndex("category_name");
                // Kiểm tra xem cột "category_name" có tồn tại không
                if (categoryNameIndex != -1) {
                    String categoryName = cursor.getString(categoryNameIndex);
                    category = new Category(categoryId, categoryName);
                } else {
                    // Xử lý khi cột không tồn tại trong kết quả truy vấn
                }
            }

            cursor.close();
        }

        return category;
    }

    public Category getCategoryByName(String categoryName) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM categorys WHERE category_name = ?", new String[]{categoryName});

        Category category = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int categoryIdIndex = cursor.getColumnIndex("category_id");

                int categoryId = cursor.getInt(categoryIdIndex);

                // Tạo đối tượng Category
                category = new Category(categoryId, categoryName);
            }

            cursor.close();
        }

        return category;
    }




    public List<Question> getListQuestion() {
        List<Question> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM questions", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int questionIdIndex = cursor.getColumnIndex("question_id");
                int questionContentIndex = cursor.getColumnIndex("question_content");
                int categoryIdIndex = cursor.getColumnIndex("cateid");

                do {
                    int questionId = cursor.getInt(questionIdIndex);
                    String questionContent = cursor.getString(questionContentIndex);
                    int categoryId = cursor.getInt(categoryIdIndex);

                    // Lấy thông tin về category tương ứng với câu hỏi
                    Category category = getCategoryById(categoryId);

                    // Tạo đối tượng Question và thêm vào danh sách
                    Question question = new Question(questionId, questionContent, category);
                    list.add(question);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        return list;
    }
    public Question getQuestionById(int questionId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM questions WHERE question_id = ?", new String[]{String.valueOf(questionId)});

        Question question = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int questionContentIndex = cursor.getColumnIndex("question_content");
                int categoryIdIndex = cursor.getColumnIndex("cateid");

                String questionContent = cursor.getString(questionContentIndex);
                int categoryId = cursor.getInt(categoryIdIndex);

                // Lấy thông tin về category tương ứng với câu hỏi
                Category category = getCategoryById(categoryId);

                // Tạo đối tượng Question
                question = new Question(questionId, questionContent, category);
            }

            cursor.close();
        }

        return question;
    }
    public Question getQuestionByContent(String questionContent) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM questions WHERE question_content = ?", new String[]{questionContent});

        Question question = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int questionIdIndex = cursor.getColumnIndex("question_id");
                int categoryIdIndex = cursor.getColumnIndex("cateid");

                int questionId = cursor.getInt(questionIdIndex);
                int categoryId = cursor.getInt(categoryIdIndex);

                // Lấy thông tin về category tương ứng với câu hỏi
                Category category = getCategoryById(categoryId);

                // Tạo đối tượng Question
                question = new Question(questionId, questionContent, category);
            }

            cursor.close();
        }

        return question;
    }

    public List<Question> getListQuestionByCategory(int categoryId) {
        List<Question> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM questions WHERE cateid = ?", new String[]{String.valueOf(categoryId)});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int questionIdIndex = cursor.getColumnIndex("question_id");
                int questionContentIndex = cursor.getColumnIndex("question_content");

                do {
                    int questionId = cursor.getInt(questionIdIndex);
                    String questionContent = cursor.getString(questionContentIndex);

                    // Tạo đối tượng Question và thêm vào danh sách
                    Question question = new Question(questionId, questionContent, getCategoryById(categoryId));
                    list.add(question);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        return list;
    }
    public Answer getAnswerByQuestion(Question question) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM answers WHERE quesid = ?", new String[]{String.valueOf(question.getQuestion_id())});

        Answer answer = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int answerIdIndex = cursor.getColumnIndex("answer_id");
                int option1Index = cursor.getColumnIndex("option1");
                int option2Index = cursor.getColumnIndex("option2");
                int option3Index = cursor.getColumnIndex("option3");
                int option4Index = cursor.getColumnIndex("option4");
                int answerIndex = cursor.getColumnIndex("dapan");

                int answerId = cursor.getInt(answerIdIndex);
                String option1 = cursor.getString(option1Index);
                String option2 = cursor.getString(option2Index);
                String option3 = cursor.getString(option3Index);
                String option4 = cursor.getString(option4Index);
                int answerValue = cursor.getInt(answerIndex);

                // Tạo đối tượng Answer
                answer = new Answer(answerId, question, option1, option2, option3, option4, answerValue);
            }

            cursor.close();
        }

        return answer;
    }


    public long addPreviousResult(PreviousResult previousResult) {
        ContentValues values = new ContentValues();
        values.put("tongdiem", previousResult.getTongdiem());
        values.put("user_id", previousResult.getPreviouts_rs_user().getId());
        values.put("cate_id", previousResult.getPreviouts_rs_cate().getCategory_id());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("previous_results", null, values);
    }

    public List<PreviousResult> getListPreviousResultByUser(int userId) {
        List<PreviousResult> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM previous_results WHERE user_id = ?", new String[]{String.valueOf(userId)});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int previousrsIdIndex = cursor.getColumnIndex("previousrs_id");
                int tongdiemIndex = cursor.getColumnIndex("tongdiem");
                int cateIdIndex = cursor.getColumnIndex("cate_id");

                do {
                    int previousrsId = cursor.getInt(previousrsIdIndex);
                    int tongdiem = cursor.getInt(tongdiemIndex);
                    int cateId = cursor.getInt(cateIdIndex);

                    // Lấy thông tin về user tương ứng với kết quả trước đó
                    User user = getUserById(userId);

                    // Lấy thông tin về category tương ứng với kết quả trước đó
                    Category category = getCategoryById(cateId);

                    // Tạo đối tượng PreviousResult và thêm vào danh sách
                    PreviousResult previousResult = new PreviousResult(previousrsId, tongdiem, user, category);
                    list.add(previousResult);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        return list;
    }



    public User checkLogin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});

        User user = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex("id");
                int imgIndex = cursor.getColumnIndex("img");
                int nameIndex = cursor.getColumnIndex("name");
                int usernameIndex = cursor.getColumnIndex("username");
                int passwordIndex = cursor.getColumnIndex("password");
                int isAdminIndex = cursor.getColumnIndex("is_admin");

                int id = cursor.getInt(idIndex);
                String img = cursor.getString(imgIndex);
                String name = cursor.getString(nameIndex);
                String dbUsername = cursor.getString(usernameIndex);
                String dbPassword = cursor.getString(passwordIndex);
                int isAdmin = cursor.getInt(isAdminIndex);

                // Kiểm tra xem username và password có khớp với dữ liệu trong cơ sở dữ liệu không
                if (username.equals(dbUsername) && password.equals(dbPassword)) {
                    // Tạo đối tượng User nếu đăng nhập thành công
                    user = new User(id, img, name, dbUsername, dbPassword, isAdmin);
                }
            }

            cursor.close();
        }

        return user;
    }

    public void deleteAllQuestions() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("questions", null, null);
    }

    public void deleteAllAnswers() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("answers", null, null);
    }


}
