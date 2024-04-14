package com.example.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.btl.R;
import com.example.btl.model.Category;

import java.util.List;

public class CategorySpinnerAdapter extends ArrayAdapter<Category> {
    private Context context;
    private List<Category> categories;

    public CategorySpinnerAdapter(Context context, List<Category> categories) {
        super(context, R.layout.item_spinner, categories);
        this.context = context;
        this.categories = categories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(categories.get(position).getCategory_name());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(categories.get(position).getCategory_name());
        return label;
    }
}

