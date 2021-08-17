package com.unixsoftect.styleklub1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public abstract class picturesadapter extends RecyclerView.Adapter<picturesadapter.Viewholder> {
Context context;
ArrayList<String> images;
ArrayList<Integer> id;

    public picturesadapter(itemopen itemopen, ArrayList<String> image, ArrayList<Integer> id) {
        this.context = itemopen;
        this.images= image;
        this.id=id;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           LayoutInflater inflater = LayoutInflater.from(parent.getContext());
           View view = inflater.inflate(R.layout.cartrowitem,parent,false);
           return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String url = "http://103.209.65.222/TheStyleKlub/Images/Banner/";
        String image = images.get(position);
        String total = url+image;
        Glide.with(context).load(url+image).into(holder.imageView);
      holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id1 = id.get(position);
                Log.e("Adapter id",String.valueOf(id1));
                 getImage(total);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.rowitem);
            imageView = itemView.findViewById(R.id.one);
        }
    }
 public abstract void getImage(String url);
}
