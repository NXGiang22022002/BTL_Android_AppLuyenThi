package com.example.btl.activity.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.adapter.CategorySpinnerAdapter;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.Answer;
import com.example.btl.model.Category;
import com.example.btl.model.Question;

import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private EditText editTextQuestionContent, editTextOption1, editTextOption2, editTextOption3, editTextOption4;
    private RadioGroup radioGroupOptions;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    private Button buttonSave, buttonCancel;

    private SQLiteHelper dbHelper;

    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        // Khởi tạo đối tượng SQLiteHelper
        dbHelper = new SQLiteHelper(this);

        // Ánh xạ các thành phần trong layout
        spinnerCategory = findViewById(R.id.add_ques_spinner);
        editTextQuestionContent = findViewById(R.id.add_ques_content);
        editTextOption1 = findViewById(R.id.add_ques_answer1);
        editTextOption2 = findViewById(R.id.add_ques_answer2);
        editTextOption3 = findViewById(R.id.add_ques_answer3);
        editTextOption4 = findViewById(R.id.add_ques_answer4);
        radioButton1 = findViewById(R.id.add_ques_radioOption1);
        radioButton2 = findViewById(R.id.add_ques_radioOption2);
        radioButton3 = findViewById(R.id.add_ques_radioOption3);
        radioButton4 = findViewById(R.id.add_ques_radioOption4);
        radioGroupOptions = findViewById(R.id.add_ques_radioGroupOptions);
        buttonSave = findViewById(R.id.add_ques_btAdd);
        buttonCancel = findViewById(R.id.add_ques_btHuy);

        // Load danh sách category từ SQLite
        loadCategoryList();

        // Xử lý sự kiện khi nhấn nút "Lưu"
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestion();
            }
        });

        // Xử lý sự kiện khi nhấn nút "Hủy"
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Đóng activity khi nhấn nút "Hủy"
            }
        });
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xác định xem RadioButton được chọn là nào
                boolean checked = ((RadioButton) v).isChecked();
                // Nếu đã được chọn, loại bỏ chọn của các RadioButton khác
                if (checked) {
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                }
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                if (checked) {
                    radioButton1.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                }
            }
        });
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                if (checked) {
                    radioButton2.setChecked(false);
                    radioButton1.setChecked(false);
                    radioButton4.setChecked(false);
                }
            }
        });
        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                if (checked) {
                    radioButton1.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton2.setChecked(false);
                }
            }
        });
    }

    // Phương thức để load danh sách category từ SQLite và hiển thị trong Spinner
    private void loadCategoryList() {
        categoryList = dbHelper.getListCate();
        CategorySpinnerAdapter adapter = new CategorySpinnerAdapter(this, categoryList);
        spinnerCategory.setAdapter(adapter);
    }

    private void addQuestion() {
        String questionContent = editTextQuestionContent.getText().toString().trim();
        int selectedCategoryId = ((Category) spinnerCategory.getSelectedItem()).getCategory_id();

        if (questionContent.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập nội dung câu hỏi", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // Thêm câu hỏi vào SQLite
            Category category = dbHelper.getCategoryById(selectedCategoryId);
            Question question = new Question(questionContent, category);
            long checkaddquestion = dbHelper.addQuestion(question);
            if(checkaddquestion ==-1){
                Toast.makeText(this,"Thêm câu hỏi thất bại",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Thêm câu hỏi ok",Toast.LENGTH_SHORT).show();

            }
            Question question_da_add = dbHelper.getQuestionById((int)checkaddquestion);
            if(question_da_add!=null){
                Toast.makeText(this,"Lấy được câu hỏi vừa thêm mới"+question_da_add.getQuestion_id(),Toast.LENGTH_SHORT).show();
            }
            addQuestionAnswer(question_da_add);
        }
    }

    private void addQuestionAnswer(Question question) {
        String answerContent1 =editTextOption1.getText().toString().trim();
        String answerContent2 =editTextOption2.getText().toString().trim();
        String answerContent3 =editTextOption3.getText().toString().trim();
        String answerContent4 =editTextOption4.getText().toString().trim();
        int isCorrect =1;
        if(radioButton1.isChecked()){
            isCorrect =1;
        }else if(radioButton2.isChecked()){
            isCorrect =2;
        }else if(radioButton3.isChecked()){
            isCorrect =3;
        }else if(radioButton4.isChecked()){
            isCorrect =4;
        }
        if(!radioButton1.isChecked()&&!radioButton2.isChecked()&&!radioButton3.isChecked()&&!radioButton4.isChecked()){
            Toast.makeText(this,"Chưa chọn đáp án đúng!",Toast.LENGTH_SHORT).show();
        } else if(answerContent1.isEmpty() || answerContent2.isEmpty() || answerContent3.isEmpty() || answerContent4.isEmpty()) {
            Toast.makeText(this,"Vui lòng nhập nội dung cho tất cả các ô đáp án!",Toast.LENGTH_SHORT).show();
        } else {
            long check = dbHelper.addAnswer(new Answer(question,answerContent1,answerContent2,answerContent3,answerContent4,isCorrect));
            if(check==-1){
                Toast.makeText(this, "Thêm câu trả lời thất bại", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Câu hỏi đã được thêm thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

}
