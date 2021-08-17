package com.unixsoftect.styleklub1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class whishlistadapter extends RecyclerView.Adapter<whishlistadapter.ViewHolder1> {
    Context context;
    Sqldatabase helperdata;
    whishlistmodal modal;
    private int value = 1;
    private int minValue = 1;
    ViewHolder1 viewHolder1;
    String  username,spinitem;
    SharedPreferences sharedPreferences;
    private int maxValue = 10;
    public whishlistadapter(whishlist whishlist, whishlistmodal modal) {
        this.context= whishlist;
        this.modal= modal;
    }

    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rowitemopen,parent,false);
        return  viewHolder1= new ViewHolder1(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        String url="http://103.209.65.222/TheStyleKlub/Images/Banner/";
        String image = modal.getImage().get(position);
        Glide.with(context).load(url+image).into(holder.image);
        holder.item.setText(modal.getItem().get(position));
        holder.tvCount.setText(modal.getSize().get(position));

//        Handler handler = new Handler();
//      final Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                selectValue(holder.spinner1,modal.getQuantity().get(position));
//                handler.postDelayed(this,1000);
//            }
//        };
//      handler.postDelayed(r,1000);

        holder.price.setText("$"+modal.getPrice().get(position).toString());
        String id = modal.getId().get(position).toString();
        String [] size = {"S","M","L","XL"};
        Integer [] type = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,size);
        holder.spinner1.setAdapter(adapter);
        //set selected spinner
        holder.spinner1.setSelection(((ArrayAdapter<String>)holder.spinner1.getAdapter()).getPosition(modal.getQuantity().get(position)));
        holder.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               spinitem =  adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//      ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_dropdown_item,type);
//      holder.spinner2.setAdapter(adapter1);
//      quantity = holder.tvCount.getText().toString();
        sharedPreferences = context.getSharedPreferences("login",Context.MODE_PRIVATE);
        username = sharedPreferences.getString("Username","");

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,shippingto.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            remove remove = new remove();
            int ide = modal.getId().get(position);
                Log.e("idadapter",String.valueOf(ide));
                remove.execute(String.valueOf(ide));
                int newposition = holder.getAdapterPosition();
                modal.image.remove(newposition);
                modal.item.remove(newposition);
                modal.price.remove(newposition);
                modal.id.remove(newposition);
                notifyItemRemoved(newposition);
                notifyItemRangeChanged(newposition,modal.item.size());
            }
        });
        holder.less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int cv = 1;
               int vc = 0;
               if (!holder.tvCount.getText().toString().equals(String.valueOf(vc)))
               {
                   int zl = Integer.valueOf(holder.tvCount.getText().toString());
                   int zx = zl-cv;
                   holder.tvCount.setText(String.valueOf(zx));
                   updatequantity updatequantity = new updatequantity();
                   updatequantity.execute(String.valueOf(zx),spinitem,username,id);
                   Log.e("idcheck",id);
                   Log.e("spincheck",spinitem);
               }
            }
        });
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cv = 1;
                int zl = Integer.valueOf(holder.tvCount.getText().toString());
                int zx = zl+cv;
                holder.tvCount.setText(String.valueOf(zx));
                updatequantity updatequantity = new updatequantity();
                updatequantity.execute(String.valueOf(zx),spinitem,username,id);
                Log.e("idcheck",id);
                Log.e("spincheck",spinitem);
            }
        });
//        int value = getValue();
//        setValue(value);

    }
    @Override
    public int getItemCount() {
        return modal.getItem().size();
    }
    public static class ViewHolder1 extends RecyclerView.ViewHolder
    {
        LinearLayout linearLayout;
        ImageView image;
        CardView more, less;
        TextView item,price;
        EditText tvCount;
        Button remove;
        Spinner spinner1 ;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            remove = itemView.findViewById(R.id.remove);
            item = itemView.findViewById(R.id.catext1);
            tvCount = itemView.findViewById(R.id.prnumber2);
            more = itemView.findViewById(R.id.more);
            less = itemView.findViewById(R.id.less);
            price = itemView.findViewById(R.id.price1);
            image  = itemView.findViewById(R.id.item1);
            linearLayout = itemView.findViewById(R.id.card);
            spinner1 = itemView.findViewById(R.id.spinner1);
        }
    }

//    private void reduce()
//    {
//        if (value > minValue)
//        {
//            value--;
//        }
//        setValue(value);
//        if (onValueChangedListene != null)
//        {
//            onValueChangedListene.onValueChange(value);
//        }
//    }
//    private void add()
//    {
//        if (value < maxValue)
//        {
//            value++;
//        }
//        setValue(value);
//        if (onValueChangedListene != null)
//        {
//            onValueChangedListene.onValueChange(value);
//        }
//    }
//     public int getValue()
//     {
//         String countStr = viewHolder1.tvCount.getText().toString();
//         if (countStr != null)
//         {
//             value = Integer.valueOf(countStr);
//         }
//         return value;
//     }
//     public void setValue(int value)
//     {
//         this.value =value;
//           viewHolder1.tvCount.setText(value +"");
//     }
//     public int getMinValue()
//     {
//         return minValue;
//     }
//     public void setMinValue(int minValue)
//     {
//         this.minValue = minValue;
//     }
//     public int getMaxValue()
//     {
//         return maxValue;
//     }
//     public void setMaxValue(int maxValue)
//     {
//         this.maxValue = maxValue;
//     }
//     public interface OnValueChangeListener {
//        public void onValueChange(int value);
//     }
//     private OnValueChangeListener onValueChangedListene;
//    public void setOnValueChangedListene(OnValueChangeListener onValueChangedListene)
//    {
//        this.onValueChangedListene = onValueChangedListene;
//    }
//    //Remove items from cart

    //delete from cart
    public class remove extends AsyncTask<String , Void , String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try
            {
                helperdata = new Sqldatabase();
                Connection con = helperdata.con();
                if (con !=null)
                {
                    PreparedStatement preparedStatement = con.prepareStatement("delete from cart where prodid =?");
                    preparedStatement.setString(1,strings[0]);
                    int rs = preparedStatement.executeUpdate();
                    if (rs > 1)
                    {
                        return "success";
                    }
                    else
                    {
                        return "failed";
                    }
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("success"))
            {
                Toast.makeText(context, "Item reomved", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public class updatequantity extends AsyncTask<String , Void ,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                helperdata = new Sqldatabase();
                Connection con = helperdata.con();
                if (con != null)
                {
                    PreparedStatement preparedStatement = con.prepareStatement("update cart set size = ?,quantity = ? where username = ? and prodid = ? ");
                    preparedStatement.setString(1 , strings[0]);
                    preparedStatement.setString(2,strings[1]);
                    preparedStatement.setString(3,strings[2]);
                    preparedStatement.setString(4,strings[3]);
                    int rs = preparedStatement.executeUpdate();
                    if (rs >0)
                    {
                        return "success";
                    }
                    else
                    {
                        return "false";
                    }
                }
                else
                {
                    return "connection failed";
                }
            }
            catch (SQLException e)
            {
                e.getMessage();
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
if (s.equals("success"))
{
    Log.e( "username", username);


}
else
{
    Log.e("nothing","nothingfound");
}
        }
    }

    public void selectValue(Spinner spinner1, String s)
    {
        for (int i = 0 ; i < spinner1.getCount(); i++)
        {
            if (spinner1.getItemAtPosition(i).equals(s))
            {
                spinner1.setSelection(i);
            }
        }
    }

}
// protected void min_ServerClick(object sender, EventArgs e)
//        {
//            decimal cv = 1;
//            decimal zl = Convert.ToDecimal(pulsss.Text.ToString());
//            decimal zx = Convert.ToDecimal((zl - cv).ToString());
//            pulsss.Text = zx.ToString();
//
//        }
//
//        protected void puls_ServerClick(object sender, EventArgs e)
//        {
//            decimal cv = 1;
//            decimal zl = Convert.ToDecimal(pulsss.Text.ToString());
//            decimal zx = Convert.ToDecimal((zl + cv).ToString());
//            pulsss.Text = zx.ToString();
//        }