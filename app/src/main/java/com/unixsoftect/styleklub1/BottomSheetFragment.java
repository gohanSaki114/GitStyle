package com.unixsoftect.styleklub1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.transform.Result;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    Button submit;
    Sqldatabase helperdata;
    SharedPreferences sharedPreferences;
    EditText shipping , billing;
    boolean shipboolean=false;
    boolean billboolean= false;
    boolean shipbill = false;
    TextInputLayout shiplayout ,billlayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        submit = v.findViewById(R.id.algo_button);
        shipping = v.findViewById(R.id.Address2);
        shiplayout = v.findViewById(R.id.shiplayout);
        billlayout = v.findViewById(R.id.billlayout);
        billing = v.findViewById(R.id.Address3);
        sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        checkifaddress1 checkifaddress = new checkifaddress1();
        checkifaddress.execute(sharedPreferences.getString("Username",""));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ship = shipping.getText().toString();
                String bill = billing.getText().toString();
                sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                address address = new address();
                if (shipbill)
                {
//                    updateaddress updateaddress = new updateaddress();
//                    updateaddress.execute();
                    Toast.makeText(getContext(), "address added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    address.execute(sharedPreferences.getString("Username", ""), bill, ship);
                    Toast.makeText(getContext(), "address inserted", Toast.LENGTH_SHORT).show();
                }



            }
        });
        return v;
    }

    public class address extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                helperdata = new Sqldatabase();
                Connection con = helperdata.con();
                if (con != null) {
                    PreparedStatement preparedStatement = con.prepareStatement("insert into UserAddress (username , BillingAddress, ShippingAddress) values(?,?,?)");
                    preparedStatement.setString(1, strings[0]);
                    preparedStatement.setString(2, strings[1]);
                    preparedStatement.setString(3, strings[2]);
                    int rs = preparedStatement.executeUpdate();
                    if (rs > 0) {
                        return "success";
                    }
                } else
                    {
                    return "No connection";
                }
            } catch (SQLException e) {
                e.getMessage();
                e.printStackTrace();
            }
            return null;
        }
    }
//    public class updateaddress extends AsyncTask<String , Void ,String>
//    {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try
//            {
//                helperdata = new Sqldatabase();
//                Connection con= helperdata.con();
//                if (con != null)
//                {
//                    PreparedStatement preparedStatement = con.prepareStatement(" ");
//                }
//            }
//            catch(SQLException e)
//            {
//                e.getMessage();
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
    public class checkifaddress1 extends AsyncTask<String, Void, String> {
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
                if (!shipadd.isEmpty() && !billadd.isEmpty())
                {
                    shipbill = true;
                }
                else if(shipadd.isEmpty())
                {
                    shipboolean= true;
                }
                else if (billadd.isEmpty())
                {
                    billboolean=true;
                }
            }
            else
            {
                Log.e("if fail", s);
            }
        }
    }
}