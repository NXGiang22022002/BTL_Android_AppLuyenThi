package com.example.btl.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.btl.R;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.Answer;
import com.example.btl.model.Category;
import com.example.btl.model.Question;
import com.example.btl.model.QuestionAnswer;

import java.io.Serializable;
import java.util.List;

public class EndTestActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvNameCate,tvNumberRight,tvPoint;
    private Button btSave,btLamlai,btOut,btXemlaibai;
    SQLiteHelper db;
    private Category category;
    private List<Question> listquestion;
    private  List<QuestionAnswer> listquestionanswer;
    int tongdiem=0,socaudung=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_test);
        initView();

    }

    private void initView() {
        tvNameCate = findViewById(R.id.end_test_NameCate);
        tvNumberRight = findViewById(R.id.end_test_NumberRight);
        tvPoint = findViewById(R.id.end_test_NumberPoint);
        btSave = findViewById(R.id.end_test_btSave);
        btLamlai = findViewById(R.id.end_test_btLamlai);
        btOut = findViewById(R.id.end_test_btOut);
        btXemlaibai = findViewById(R.id.end_test_btXemlaibailam);
        db = new SQLiteHelper(this);
        Intent intent = getIntent();
        category = (Category) intent.getSerializableExtra("category");
        listquestion = (List<Question>) intent.getSerializableExtra("listquestion");
        listquestionanswer = (List<QuestionAnswer>) intent.getSerializableExtra("listquestionanswer");
        tinhDiem();
        tvNameCate.setText(category.getCategory_name());
        tvNumberRight.setText(socaudung+"/"+listquestion.size());
        tvPoint.setText(tongdiem+"");
        btSave.setOnClickListener(this);
        btOut.setOnClickListener(this);
        btLamlai.setOnClickListener(this);
        btXemlaibai.setOnClickListener(this);
    }

    private void tinhDiem() {
        for(Question i : listquestion){
            Answer answer= db.getAnswerByQuestion(i);
            for (QuestionAnswer y : listquestionanswer){
                if (y.getQuestionanswer_id() == i.getQuestion_id()){
                    if(y.getQuestionanswer_dapandachon()==answer.getDapan()){
                        tongdiem+=4;
                        socaudung++;
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v==btLamlai){
            Intent intent = new Intent(EndTestActivity.this, TestActivity.class);
            intent.putExtra("category", category);
            intent.putExtra("listquestion",(Serializable) listquestion);
            startActivity(intent);
        }else if(v==btOut){
            Intent intent = new Intent(EndTestActivity.this,MainActivity.class);
            startActivity(intent);
        } else if (v==btSave) {

        }else if (v==btXemlaibai){
            Intent rs = new Intent(EndTestActivity.this,XemlaibaiActivity.class);
            rs.putExtra("listquestionanswer",(Serializable) listquestionanswer);
            rs.putExtra("category",category);
            rs.putExtra("listquestion",(Serializable) listquestion);
            startActivity(rs);
        }

    }
}