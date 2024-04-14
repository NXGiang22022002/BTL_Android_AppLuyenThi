package com.example.btl.activity.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl.R;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.Category;

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eTenmonhoc;
    private Button btadd,bthuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        eTenmonhoc = findViewById(R.id.add_cate_name);
        btadd = findViewById(R.id.add_cate_btAdd);
        bthuy = findViewById(R.id.add_cate_btHuy);
        btadd.setOnClickListener(this);
        bthuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btadd){
            String ten = eTenmonhoc.getText().toString();
            if(!ten.isEmpty()){
                Category category = new Category(ten);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addCategory(category);
                finish();
            }
        }
        if(v==bthuy){
            finish();
        }
    }
}