package com.example.btl.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.activity.user.StartTestActivity;
import com.example.btl.activity.user.TestActivity;
import com.example.btl.adapter.CategoryQuestionAdapter;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.Answer;
import com.example.btl.model.Category;
import com.example.btl.model.Question;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements CategoryQuestionAdapter.ItemListener {
    private RecyclerView recyclerView;
    private CategoryQuestionAdapter adapter;
    private SQLiteHelper db;
    private ViewFlipper viewFlipper;
    private DrawerLayout drawerLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fragment_home_recycleview);
        viewFlipper = view.findViewById(R.id.fragment_home_viewflipper);
        drawerLayout = requireActivity().findViewById(R.id.fragment_home_drawlayout);
        adapter = new CategoryQuestionAdapter();
        db = new SQLiteHelper(getContext());
        ActionViewFlipper();
//        Category category2 = new Category("Category 3");
//        db.addCategory(category2);
//        Category category1 = new Category("Category 1");
//        db.addCategory(category1);
//        Category category2 = new Category("Category 2");
//        db.addCategory(category2);

//        // Thêm dữ liệu cho câu hỏi 1 của Category 1
//        Category category_1 = db.getCategoryByName("Category 1");
//        Question question11 = new Question("Question 5 for Category 1", category_1);
//        db.addQuestion(question11);
//        Question q1 = db.getQuestionByContent("Question 5 for Category 1");
//        Answer answer111 = new Answer(q1, "Option 1 for Question 1", "Option 2 for Question 1", "Option 3 for Question 1", "Option 4 for Question 1", 1);
//        db.addAnswer(answer111);



        List<Category> list = db.getListCate();
        adapter.setListCate(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    private void ActionViewFlipper() {
        List<Integer> listImages = new ArrayList<>();
        listImages.add(R.drawable.quangcao1);
        listImages.add(R.drawable.quangcao2);
        listImages.add(R.drawable.quangcao3);

        for (int i = 0; i < listImages.size(); i++) {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            imageView.setImageResource(listImages.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        Animation slideIn = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_in_right);
        Animation slideOut = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slideIn);
        viewFlipper.setOutAnimation(slideOut);
    }


    @Override
    public void onItemClick(View view, int position) {
        Category category = adapter.getCate(position);
        Intent intent = new Intent(getActivity(), StartTestActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    }
