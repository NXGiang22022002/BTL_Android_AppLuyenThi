package com.example.btl.activity.user;

import static com.example.btl.R.id.start_test_NameCate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.btl.R;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.Category;
import com.example.btl.model.Question;

import java.io.Serializable;
import java.util.List;

public class StartTestActivity extends AppCompatActivity {
    private TextView tvNamecate,tvSizecate;
    private Button btstart;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        tvNamecate = findViewById(start_test_NameCate);
        tvSizecate = findViewById(R.id.start_test_SizeCate);
        btstart = findViewById(R.id.start_test_btStart);
        Intent intent = getIntent();
        Category category = (Category) intent.getSerializableExtra("category");
        tvNamecate.setText(category.getCategory_name());
        db = new SQLiteHelper(this);
        List<Question> listquestion = db.getListQuestionByCategory(category.getCategory_id());
        tvSizecate.setText(listquestion.size()+"");
        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartTestActivity.this, TestActivity.class);
                intent.putExtra("category", category);
                intent.putExtra("listquestion",(Serializable) listquestion);
                startActivity(intent);            }
        });

    }
}