package com.example.btl.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.Answer;
import com.example.btl.model.Category;
import com.example.btl.model.Question;
import com.example.btl.model.QuestionAnswer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvNameCate,tvQuestion,tvNumberques,tvTime,tvQuessize;
    private Button btdapan1,btdapan2,btdapan3,btdapan4,btNextQues;
    List<Question> listquestion;
    List<QuestionAnswer> listquestionanswer = new ArrayList<>();
    Category category;
    long time=60000;
    CountDownTimer countdowntimer;
    int questionCounter=0 ,questionSize,dapanchon=0;
    Question currentquestion;
    SQLiteHelper db;
    boolean trangthaitraloi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        Intent intent = getIntent();
        category =(Category) intent.getSerializableExtra("category");
        listquestion = (List<Question>) intent.getSerializableExtra("listquestion");
        tvNameCate.setText(category.getCategory_name());
        questionSize = listquestion.size();
        tvQuessize.setText(questionSize+"");
        db = new SQLiteHelper(this);
        //Hiển thị câu hỏi và đáp án
        showQuestion();
        btdapan1.setOnClickListener(this);
        btdapan2.setOnClickListener(this);
        btdapan3.setOnClickListener(this);
        btdapan4.setOnClickListener(this);
        btNextQues.setOnClickListener(this);

    }

    private void showQuestion() {
        btdapan1.setBackgroundColor(Color.WHITE);
        btdapan2.setBackgroundColor(Color.WHITE);
        btdapan3.setBackgroundColor(Color.WHITE);
        btdapan4.setBackgroundColor(Color.WHITE);
        if(questionCounter<questionSize){
            currentquestion = listquestion.get(questionCounter);
            //hiển thị nội dung câu hỏi
            tvQuestion.setText(currentquestion.getQuestion_content());
            //hiển thị đáp án
            Answer answer = db.getAnswerByQuestion(currentquestion);
            btdapan1.setText(answer.getOption1());
            btdapan2.setText(answer.getOption2());
            btdapan3.setText(answer.getOption3());
            btdapan4.setText(answer.getOption4());
            time=60000;
            // tăng sau mỗi câu hỏi
            questionCounter++;
            tvNumberques.setText(questionCounter+"");
            // giá trị false== chưa trả lời
            trangthaitraloi =false;
            dapanchon=0;
            timeCountDown();
        }else{
            finishQuestion();
        }
    }

    private void timeCountDown() {
        countdowntimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                // hết h
                time =0;
                updateCountDown();
                SaveAnswer();
            }
        }.start();
    }
    private void SaveAnswer(){
        listquestionanswer.add(new QuestionAnswer(currentquestion.getQuestion_id(),dapanchon));
        if(questionCounter<questionSize){
            btNextQues.setText("Câu hỏi kế tiếp");
        }else{
            btNextQues.setText("Hoàn thành");
        }
        countdowntimer.cancel();
    }
    private void updateCountDown(){
        int minutes = (int) ((time/1000)/60);
        int seconds = (int) ((time/1000)%60);
        // định dạng kiểu time
        String timeformat = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        tvTime.setText(timeformat);
        if(time<10000){
            tvTime.setTextColor(Color.RED);
        }else{
            tvTime.setTextColor(Color.BLACK);
        }
    }

    private void finishQuestion() {
        Intent rs = new Intent(TestActivity.this,EndTestActivity.class);
        rs.putExtra("listquestionanswer",(Serializable) listquestionanswer);
        rs.putExtra("category",category);
        rs.putExtra("listquestion",(Serializable) listquestion);
        startActivity(rs);
    }

    private void initView() {
        tvNameCate = findViewById(R.id.test_NameCate);
        tvQuestion = findViewById(R.id.test_QuestionContent);
        tvNumberques = findViewById(R.id.test_number);
        tvQuessize = findViewById(R.id.test_QuesSize);
        tvTime = findViewById(R.id.test_Time);
        btdapan1 = findViewById(R.id.test_btDapan1);
        btdapan2 = findViewById(R.id.test_btDapan2);
        btdapan3 = findViewById(R.id.test_btDapan3);
        btdapan4 = findViewById(R.id.test_btDapan4);
        btNextQues = findViewById(R.id.test_btNext);
    }

    @Override
    public void onClick(View v) {
        if(v==btdapan1){
            btdapan1.setBackgroundColor(Color.GREEN);
            trangthaitraloi =true;
            dapanchon =1;
            btdapan2.setBackgroundColor(Color.WHITE);
            btdapan3.setBackgroundColor(Color.WHITE);
            btdapan4.setBackgroundColor(Color.WHITE);
        }else if(v==btdapan2){
            btdapan2.setBackgroundColor(Color.GREEN);
            trangthaitraloi =true;
            dapanchon =2;
            btdapan1.setBackgroundColor(Color.WHITE);
            btdapan3.setBackgroundColor(Color.WHITE);
            btdapan4.setBackgroundColor(Color.WHITE);
        }
        else if(v==btdapan3){
            btdapan3.setBackgroundColor(Color.GREEN);
            trangthaitraloi =true;
            dapanchon =3;
            btdapan1.setBackgroundColor(Color.WHITE);
            btdapan2.setBackgroundColor(Color.WHITE);
            btdapan4.setBackgroundColor(Color.WHITE);
        }
        else if(v==btdapan4){
            btdapan4.setBackgroundColor(Color.GREEN);
            trangthaitraloi =true;
            dapanchon =4;
            btdapan1.setBackgroundColor(Color.WHITE);
            btdapan2.setBackgroundColor(Color.WHITE);
            btdapan3.setBackgroundColor(Color.WHITE);
        }else if(v==btNextQues){
            if(trangthaitraloi==true){
                SaveAnswer();
                showQuestion();
            }else{
                Toast.makeText(this,"Hãy họn đáp án cho câu hỏi này!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}