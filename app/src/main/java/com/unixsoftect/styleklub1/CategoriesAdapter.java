package com.unixsoftect.styleklub1;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder1> {


    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view1 = inflater.inflate(R.layout.itemcategories,parent,false);
        return new ViewHolder1(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }
    public static class ViewHolder1 extends RecyclerView.ViewHolder
    {

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
        }
    }

}