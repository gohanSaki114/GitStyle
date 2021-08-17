package com.unixsoftect.styleklub1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class shippingto extends AppCompatActivity {
RecyclerView recyclerView;
MaterialButton next ,Address;
shippingmodal modal;
shippingadapter adapter;
Sqldatabase helperdata;
    SharedPreferences sharedPreferences;
    boolean addresscheck = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shippingto);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        next = findViewById(R.id.newaddress);
        Address = findViewById(R.id.next);
        recyclerView = findViewById(R.id.list_item3);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        checkifaddress checkifaddress = new checkifaddress();
        checkifaddress.execute(sharedPreferences.getString("Username",""));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shippingto.this,payment.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if no record found
                if(addresscheck)
                {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getSupportFragmentManager(),"ModalBottomSheet");
                }
            }
        });
    }
    //check if database has records and retrive data
    public class checkifaddress extends AsyncTask<String, Void, String> {
        String user,billadd,shipadd;
        ArrayList<String> address = new ArrayList<String>();
        @Override
        protected String doInBackground(String... strings) {
            try {
                helperdata = new Sqldatabase();
                Connection con = helperdata.con();
                if (con != null) {
                    PreparedStatement preparedStatement = con.prepareStatement("select * from Useraddress where username = ?");
                    preparedStatement.setString(1,strings[0]);
                    ResultSet rs = preparedStatement.executeQuery();
                    if (rs.isBeforeFirst())
                    {
                        while(rs.next())
                        {
                            user = rs.getString(1);
                            billadd = rs.getString(2);
                            shipadd = rs.getString(3);
                        }
                        address.add(shipadd);
                        address.add(billadd);
                        return "success";
                    }
                    else
                    {
                        return "Failed";
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
     //hide button if record found
            if (s.equals("success"))
            {
                modal= new shippingmodal(user,address);
                adapter = new shippingadapter(shippingto.this,modal,new BottomSheetFragment());
                recyclerView.setAdapter(adapter);
                addresscheck = true;
                Address.setText("change address");

                Log.e("check address status",s);
            }
            else
            {
                Log.e("if fail", s);
            }
        }
    }
}
//  update query with conditions
//  update Useraddress
//  set BillingAddress=Case
//  when BillingAddress=@address then ''
//  else BillingAddress
//  end,
//  ShippingAddress=Case
//  when ShippingAddress=@address then ''
//  else ShippingAddress
//  end
//  where username='sagar' and BillingAddress=@address or ShippingAddress=@address;