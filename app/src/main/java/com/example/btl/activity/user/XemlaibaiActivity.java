package com.example.btl.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class XemlaibaiActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvNameCate,tvNumber,tvQuesContent;
    private Button btdapan1,btdapan2,btdapan3,btdapan4,btNextQues,btBackQues;
    List<Question> listquestion;
    List<QuestionAnswer> listquestionanswer = new ArrayList<>();
    Category category;
    Question currentquestion;
    int questionCounter=0 ,questionSize;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemlaibai);
        initView();
        Intent intent = getIntent();
        category =(Category) intent.getSerializableExtra("category");
        listquestion = (List<Question>) intent.getSerializableExtra("listquestion");
        listquestionanswer = (List<QuestionAnswer>) intent.getSerializableExtra("listquestionanswer");
        tvNameCate.setText(category.getCategory_name());
        questionSize = listquestion.size();
        db = new SQLiteHelper(this);
        showQuestion();
        btNextQues.setOnClickListener(this);
        btBackQues.setOnClickListener(this);

    }

    private void showQuestion() {
        btdapan1.setBackgroundColor(Color.WHITE);
        btdapan2.setBackgroundColor(Color.WHITE);
        btdapan3.setBackgroundColor(Color.WHITE);
        btdapan4.setBackgroundColor(Color.WHITE);
        if(questionCounter<questionSize){
            currentquestion = listquestion.get(questionCounter);
            //hiển thị nội dung câu hỏi
            tvQuesContent.setText(currentquestion.getQuestion_content());
            //hiển thị đáp án
            Answer answer = db.getAnswerByQuestion(currentquestion);
            btdapan1.setText(answer.getOption1());
            btdapan2.setText(answer.getOption2());
            btdapan3.setText(answer.getOption3());
            btdapan4.setText(answer.getOption4());
            // tăng sau mỗi câu hỏi
            questionCounter++;
            tvNumber.setText(questionCounter+"/"+questionSize);
            for(QuestionAnswer i : listquestionanswer){
                if(i.getQuestionanswer_id()==currentquestion.getQuestion_id()){
                    if(answer.getDapan()==i.getQuestionanswer_dapandachon()){
                        if(i.getQuestionanswer_dapandachon()==1){
                            btdapan1.setBackgroundColor(Color.GREEN);
                        }else if(i.getQuestionanswer_dapandachon()==2){
                            btdapan2.setBackgroundColor(Color.GREEN);
                        }else if(i.getQuestionanswer_dapandachon()==3){
                            btdapan3.setBackgroundColor(Color.GREEN);
                        }else if(i.getQuestionanswer_dapandachon()==4){
                            btdapan4.setBackgroundColor(Color.GREEN);
                        }
                    }else {
                        if (answer.getDapan() == 1) {
                            btdapan1.setBackgroundColor(Color.GREEN);
                            if (i.getQuestionanswer_dapandachon() == 2) {
                                btdapan2.setBackgroundColor(Color.RED);
                            } else if (i.getQuestionanswer_dapandachon() == 3) {
                                btdapan3.setBackgroundColor(Color.RED);
                            } else if (i.getQuestionanswer_dapandachon() == 4) {
                                btdapan4.setBackgroundColor(Color.RED);
                            }
                        } else if (answer.getDapan() == 2) {
                            btdapan1.setBackgroundColor(Color.GREEN);
                            if (i.getQuestionanswer_dapandachon() == 1) {
                                btdapan2.setBackgroundColor(Color.RED);
                            } else if (i.getQuestionanswer_dapandachon() == 3) {
                                btdapan3.setBackgroundColor(Color.RED);
                            } else if (i.getQuestionanswer_dapandachon() == 4) {
                                btdapan4.setBackgroundColor(Color.RED);
                            }
                        }else if (answer.getDapan() == 3) {
                            btdapan1.setBackgroundColor(Color.GREEN);
                            if (i.getQuestionanswer_dapandachon() == 1) {
                                btdapan2.setBackgroundColor(Color.RED);
                            } else if (i.getQuestionanswer_dapandachon() == 2) {
                                btdapan3.setBackgroundColor(Color.RED);
                            } else if (i.getQuestionanswer_dapandachon() == 4) {
                                btdapan4.setBackgroundColor(Color.RED);
                            }
                        }else if (answer.getDapan() == 4) {
                            btdapan1.setBackgroundColor(Color.GREEN);
                            if (i.getQuestionanswer_dapandachon() == 1) {
                                btdapan2.setBackgroundColor(Color.RED);
                            } else if (i.getQuestionanswer_dapandachon() == 3) {
                                btdapan3.setBackgroundColor(Color.RED);
                            } else if (i.getQuestionanswer_dapandachon() == 2) {
                                btdapan4.setBackgroundColor(Color.RED);
                            }
                        }
                    }
                }
            }

        }else{
            finishQuestion();
        }
    }

    private void showPreviousQuestion() {
        btdapan1.setBackgroundColor(Color.WHITE);
        btdapan2.setBackgroundColor(Color.WHITE);
        btdapan3.setBackgroundColor(Color.WHITE);
        btdapan4.setBackgroundColor(Color.WHITE);

        if (questionCounter >= 0 && questionCounter < questionSize) {
            QuestionAnswer i = listquestionanswer.get(questionCounter);
            currentquestion = listquestion.get(questionCounter);
            // Hiển thị nội dung câu hỏi
            tvQuesContent.setText(currentquestion.getQuestion_content());
            // Hiển thị đáp án
            Answer answer = db.getAnswerByQuestion(currentquestion);
            btdapan1.setText(answer.getOption1());
            btdapan2.setText(answer.getOption2());
            btdapan3.setText(answer.getOption3());
            btdapan4.setText(answer.getOption4());
            // Giảm chỉ số của câu hỏi
            tvNumber.setText((questionCounter + 1) + "/" + questionSize);
            if(i.getQuestionanswer_id()==currentquestion.getQuestion_id()){
                if(answer.getDapan()==i.getQuestionanswer_dapandachon()){
                    if(i.getQuestionanswer_dapandachon()==1){
                        btdapan1.setBackgroundColor(Color.GREEN);
                    }else if(i.getQuestionanswer_dapandachon()==2){
                        btdapan2.setBackgroundColor(Color.GREEN);
                    }else if(i.getQuestionanswer_dapandachon()==3){
                        btdapan3.setBackgroundColor(Color.GREEN);
                    }else if(i.getQuestionanswer_dapandachon()==4){
                        btdapan4.setBackgroundColor(Color.GREEN);
                    }
                }else {
                    if (answer.getDapan() == 1) {
                        btdapan1.setBackgroundColor(Color.GREEN);
                        if (i.getQuestionanswer_dapandachon() == 2) {
                            btdapan2.setBackgroundColor(Color.RED);
                        } else if (i.getQuestionanswer_dapandachon() == 3) {
                            btdapan3.setBackgroundColor(Color.RED);
                        } else if (i.getQuestionanswer_dapandachon() == 4) {
                            btdapan4.setBackgroundColor(Color.RED);
                        }
                    } else if (answer.getDapan() == 2) {
                        btdapan1.setBackgroundColor(Color.GREEN);
                        if (i.getQuestionanswer_dapandachon() == 1) {
                            btdapan2.setBackgroundColor(Color.RED);
                        } else if (i.getQuestionanswer_dapandachon() == 3) {
                            btdapan3.setBackgroundColor(Color.RED);
                        } else if (i.getQuestionanswer_dapandachon() == 4) {
                            btdapan4.setBackgroundColor(Color.RED);
                        }
                    }else if (answer.getDapan() == 3) {
                        btdapan1.setBackgroundColor(Color.GREEN);
                        if (i.getQuestionanswer_dapandachon() == 1) {
                            btdapan2.setBackgroundColor(Color.RED);
                        } else if (i.getQuestionanswer_dapandachon() == 2) {
                            btdapan3.setBackgroundColor(Color.RED);
                        } else if (i.getQuestionanswer_dapandachon() == 4) {
                            btdapan4.setBackgroundColor(Color.RED);
                        }
                    }else if (answer.getDapan() == 4) {
                        btdapan1.setBackgroundColor(Color.GREEN);
                        if (i.getQuestionanswer_dapandachon() == 1) {
                            btdapan2.setBackgroundColor(Color.RED);
                        } else if (i.getQuestionanswer_dapandachon() == 3) {
                            btdapan3.setBackgroundColor(Color.RED);
                        } else if (i.getQuestionanswer_dapandachon() == 2) {
                            btdapan4.setBackgroundColor(Color.RED);
                        }
                    }
                }
            }
        } else {
            Toast.makeText(this,"Đây là câu hỏi đầu tiên",Toast.LENGTH_SHORT).show();
        }
    }

    private void finishQuestion() {
        btNextQues.setText("Hoàn thành");
        Intent rs = new Intent(XemlaibaiActivity.this,EndTestActivity.class);
        rs.putExtra("listquestionanswer",(Serializable) listquestionanswer);
        rs.putExtra("category",category);
        rs.putExtra("listquestion",(Serializable) listquestion);
        startActivity(rs);
    }

    private void initView() {
        tvNameCate = findViewById(R.id.xemlai_NameCate);
        tvNumber = findViewById(R.id.xemlai_number);
        tvQuesContent = findViewById(R.id.xemlai_QuestionContent);
        btdapan1 = findViewById(R.id.xemlai_btDapan1);
        btdapan2 = findViewById(R.id.xemlai_btDapan2);
        btdapan3 = findViewById(R.id.xemlai_btDapan3);
        btdapan4 = findViewById(R.id.xemlai_btDapan4);
        btBackQues = findViewById(R.id.xemlai_btBack);
        btNextQues = findViewById(R.id.xemlai_btNext);
    }

    @Override
    public void onClick(View v) {
        if(v==btNextQues){
            showQuestion();
        }else if(v==btBackQues){
            if (questionCounter > 0) {
                questionCounter--;
                showPreviousQuestion();
            } else {
                Toast.makeText(this,"Đây là câu hỏi đầu tiên",Toast.LENGTH_SHORT).show();
            }
        }
    }
}