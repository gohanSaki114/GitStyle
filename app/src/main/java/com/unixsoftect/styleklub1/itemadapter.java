package com.unixsoftect.styleklub1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unixsoftect.styleklub1.modals.mainmodal;

public class itemadapter extends RecyclerView.Adapter<itemadapter.ViewHolder> {
    Context context;
    mainmodal modal;
   public boolean seeall1 =false;

    public itemadapter(Context context, mainmodal modal)
    {
       this.context= context;
       this.modal=modal;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view1 = inflater.inflate(R.layout.shopitems,parent,false);
        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull itemadapter.ViewHolder holder, int position) {

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,itemopen.class);
                Bundle extras = new Bundle();
                extras.putInt("openitem", (modal.getId().get(position)));
                Log.e("aadsasadsa",(modal.getId().get(position).toString()));
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });
        holder.price.setText(modal.getPrice().get(position).toString()+"$");
        holder.shirt.setText(modal.getItem().get(position));
        String url="http://103.209.65.222/TheStyleKlub/Images/Banner/";
        String image = modal.getImage().get(position);
        Log.e("log the image",image);
        Glide.with(context).load(url+image).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (seeall1 == true)
        {
            return  modal.getId().size();
        }
        else
        return 3;
//        return  modal.getId().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView shirt,price;
         ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.items);
            linearLayout = itemView.findViewById(R.id.shoplayout);
            shirt = itemView.findViewById(R.id.itemtext);
            price = itemView.findViewById(R.id.price7);
        }
    }
}
//http://103.209.65.222/TheStyleKlub/Images/Banner/
//boy2.png