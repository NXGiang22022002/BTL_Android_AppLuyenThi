package com.example.btl.activity.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.adapter.CategoryQuestionAdapter;
import com.example.btl.adapter.CategorySpinnerAdapter;
import com.example.btl.adapter.RecycleQuestionAdapter;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.Category;
import com.example.btl.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuanlyBaiActivity extends AppCompatActivity {
    private Spinner spinner;
    private RecyclerView recyclerView;
    private CategorySpinnerAdapter adapter;
    private RecycleQuestionAdapter questionAdapter;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_bai);

        // Khởi tạo đối tượng SQLiteHelper
        db = new SQLiteHelper(this);
//        db.deleteAllQuestions();
//        db.deleteAllAnswers();

        spinner = findViewById(R.id.quanlybai_spinner);
        recyclerView = findViewById(R.id.quanlybai_recycleview);
        // Khởi tạo dữ liệu cho Spinner và RecyclerView
        List<Category> categoryList = db.getListCate();
        adapter = new CategorySpinnerAdapter(this, categoryList);
        spinner.setAdapter(adapter);

        // Xử lý sự kiện chọn category từ Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category selectedCategory = (Category) parent.getItemAtPosition(position);
                List<Question> questions = db.getListQuestionByCategory(selectedCategory.getCategory_id());
                updateRecyclerView(questions);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void updateRecyclerView(List<Question> questions) {
        questionAdapter = new RecycleQuestionAdapter(questions,db);
        recyclerView.setAdapter(questionAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
    }
}
