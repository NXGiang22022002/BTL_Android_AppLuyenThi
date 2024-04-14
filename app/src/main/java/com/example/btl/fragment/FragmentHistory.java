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
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.activity.user.TestActivity;
import com.example.btl.adapter.CategoryQuestionAdapter;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.Answer;
import com.example.btl.model.Category;
import com.example.btl.model.Question;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment implements CategoryQuestionAdapter.ItemListener {
    private RecyclerView recyclerView;
    private CategoryQuestionAdapter adapter;
    private SQLiteHelper db;
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.frag_history_recycleView);
        db = new SQLiteHelper(getContext());
        adapter = new CategoryQuestionAdapter();
        List<Category> list = db.getListCate();
        adapter.setListCate(list);
        tv = view.findViewById(R.id.frag_history_tv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }
    @Override
    public void onItemClick(View view, int position) {
        Category category = adapter.getCate(position);
        Intent intent = new Intent(getActivity(), TestActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }



}
