package com.unixsoftect.styleklub1;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class shippingadapter extends RecyclerView.Adapter<shippingadapter.ViewHolder> {
Context context;
shippingmodal modal;
BottomSheetFragment bottomSheetFragment;
    public shippingadapter(shippingto context, shippingmodal modal, BottomSheetFragment bottomSheetFragment) {
        this.context = context;
        this.modal = modal;
        this.bottomSheetFragment = bottomSheetFragment;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.shippingrowitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.address.setText(modal.getAddress().get(position));
       String upaddress = modal.getAddress().get(position);
       if (holder.address.getText().equals(""))
       {
           holder.tick.setVisibility(View.GONE);
       }
    }

    @Override
    public int getItemCount() {
        return modal.getAddress().size();
    }
public static class ViewHolder extends RecyclerView.ViewHolder
{
    TextView address;
    Button tick;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
               address = itemView.findViewById(R.id.address);
               tick = itemView.findViewById(R.id.tick);
    }
}
}
