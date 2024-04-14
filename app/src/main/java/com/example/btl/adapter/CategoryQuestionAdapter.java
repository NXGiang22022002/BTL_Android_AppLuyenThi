package com.example.btl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryQuestionAdapter extends RecyclerView.Adapter<CategoryQuestionAdapter.CategoryQuestionViewHolder> {
    List<Category> list;
    ItemListener itemListener;


    public CategoryQuestionAdapter() {
        list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
    public List<Category> getListCate() {
        return list;
    }
    public Category getCate(int pos){
        return list.get(pos);
    }
    public void setListCate(List<Category> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cate_ques,parent,false);
        return new CategoryQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryQuestionViewHolder holder, int position) {
        Category category = list.get(position);
        holder.Cate_name.setText(category.getCategory_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryQuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Cate_name;

        public CategoryQuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            Cate_name = itemView.findViewById(R.id.item_cate_ques_Name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemListener!=null){
                itemListener.onItemClick(v,getAdapterPosition());
            }
        }
    }
    public interface ItemListener{
        void onItemClick(View view,int position);
    }
}
