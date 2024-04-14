package com.example.btl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.activity.Utils.Utils;
import com.example.btl.activity.admin.MainActivityAdmin;
import com.example.btl.activity.user.MainActivity;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.User;

public class LogInActivity extends AppCompatActivity {
    private SQLiteHelper db;
    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        db = new SQLiteHelper(this);
        etUsername = findViewById(R.id.dangnhap_tk);
        etPassword = findViewById(R.id.dangnhap_mk);
        btnLogin = findViewById(R.id.dangnhap_btndangnhap);
        btnRegister = findViewById(R.id.dangnhap_btndangky);
//        User user = new User("https://png.pngtree.com/png-vector/20230316/ourmid/pngtree-admin-and-customer-service-job-vacancies-vector-png-image_6650726.png",
//                "ADMIN","admin","admin",1);
//        db.addUser(user);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LogInActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    dangNhap(username, password);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this,RegistryActivity.class);
                startActivity(intent);
            }
        });

    }
        private void dangNhap(String tk, String mk){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext());
        User user = sqLiteHelper.checkLogin(tk,mk);
        if(user!=null){
            Utils.user_current = user;
            sqLiteHelper.close();
            SQLiteHelper sqlpreviousresult = new SQLiteHelper(getApplicationContext());
            Utils.previousResultList = sqlpreviousresult.getListPreviousResultByUser(Utils.user_current.getId());
            sqlpreviousresult.close();
            if(Utils.user_current.getIs_admin()==1){
                Intent intent = new Intent(getApplicationContext(), MainActivityAdmin.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Sai thông tin đăng nhập!",Toast.LENGTH_SHORT).show();
        }
    }
}