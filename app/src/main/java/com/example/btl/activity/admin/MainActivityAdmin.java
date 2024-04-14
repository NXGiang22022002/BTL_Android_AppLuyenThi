package com.example.btl.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.btl.activity.*;
import com.example.btl.R;
import com.example.btl.activity.LogInActivity;
import com.example.btl.adapter.CategoryQuestionAdapter;
import com.example.btl.model.Category;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityAdmin extends AppCompatActivity implements View.OnClickListener{
    ViewFlipper viewFlipper;
    DrawerLayout drawerLayout;
//    Button btaddcate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        initview();
        ActionViewFlipper();
//        btaddcate.setOnClickListener(this);
    }
   // quảng cáo tự động
    private void ActionViewFlipper() {
        List<Integer> listImages = new ArrayList<>();
        listImages.add(R.drawable.quangcao1);
        listImages.add(R.drawable.quangcao2);
        listImages.add(R.drawable.quangcao3);

        for (int i = 0; i < listImages.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(listImages.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        Animation slideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slideOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slideIn);
        viewFlipper.setOutAnimation(slideOut);
    }





    private void initview() {
        viewFlipper = findViewById(R.id.trangchu_viewflipper);
//        btaddcate = findViewById(R.id.admin_bt_addCate);
        drawerLayout = findViewById(R.id.trangchu_drawlayout);
    }

    @Override
    public void onClick(View v) {
//        if(v==btaddcate){
//            Intent intent = new Intent(MainActivityAdmin.this, AddCategoryActivity.class);
//            startActivity(intent);
//        }

    }
    // tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //bắt sự kiện cho menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid = item.getItemId();
        if(itemid==R.id.mAddCate){
            Intent intent = new Intent(MainActivityAdmin.this, AddCategoryActivity.class);
            startActivity(intent);
        }else if(itemid==R.id.mAddQues){
            Intent intent = new Intent(MainActivityAdmin.this,AddQuestionActivity.class);
            startActivity(intent);
        }else if(itemid==R.id.mQuanlyUser){
            Intent intent = new Intent(MainActivityAdmin.this, QuanlyUserActivity.class);
            startActivity(intent);
        }else if(itemid==R.id.mQuanlyBai){
            Intent intent = new Intent(MainActivityAdmin.this, QuanlyBaiActivity.class);
            startActivity(intent);
        }else if(itemid==R.id.mLogOut){
//            FirebaseAuth.getInstance().signOut();
            // Tiếp theo, bạn có thể chuyển người dùng đến màn hình đăng nhập hoặc màn hình khác tùy thuộc vào logic của ứng dụng
            // Ví dụ:
            startActivity(new Intent(MainActivityAdmin.this, LogInActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}