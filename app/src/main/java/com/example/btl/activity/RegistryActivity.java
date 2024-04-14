package com.example.btl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.User;

public class RegistryActivity extends AppCompatActivity {
    private EditText etName, etAvatar, etUsername, etPassword, etRePassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registy);
        etName = findViewById(R.id.dangky_name);
        etAvatar = findViewById(R.id.dangky_img);
        etUsername = findViewById(R.id.dangky_tk);
        etPassword = findViewById(R.id.dangky_mk);
        etRePassword = findViewById(R.id.dangky_remk);
        btnRegister = findViewById(R.id.dangky_btndangky);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String img = etAvatar.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String rePassword = etRePassword.getText().toString().trim();

                // Kiểm tra xác nhận mật khẩu
                if (!password.equals(rePassword)) {
                    Toast.makeText(RegistryActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                } else {
                    User newUser = new User(img, name, username, password, 0);
                    boolean success = addUser(newUser);
                    if (success) {
                        Toast.makeText(RegistryActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegistryActivity.this, "Đăng ký thất bại! Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean addUser(User user) {
        SQLiteHelper db = new SQLiteHelper(this);
        long result = db.addUser(user);
        return result != -1;
    }
}
