package com.unixsoftect.styleklub1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class itemopen extends AppCompatActivity {
    RecyclerView recyclerView;
    Button Addtocart,Buynow;
    SharedPreferences sharedPreferences;
    SubsamplingScaleImageView bigpic;
    picturesadapter adapterpic;
    ImageView imageView;
    EditText prnumber;
    Spinner spinner;
    String username ="";
    TextView topdescription,discount1,price1,description1,description2,description3,productdetails1;
    Sqldatabase helperdata;
    boolean state = false;
    String idd ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemopen);
        Addtocart = findViewById(R.id.Addtocart);
        Buynow = findViewById(R.id.Buynow);
        prnumber = findViewById(R.id.prnumber3);
        spinner = findViewById(R.id.spinner5);
        topdescription = findViewById(R.id.textitems);
        recyclerView = findViewById(R.id.picitems);
        discount1 = findViewById(R.id.textitems2);
        price1 = findViewById(R.id.textitems3);
        bigpic = findViewById(R.id.giantimage);
        description1 = findViewById(R.id.textitems4);
        description2 = findViewById(R.id.textitems5);
        description3 = findViewById(R.id.textitems6);
        productdetails1 = findViewById(R.id.details);
        String [] size = {"S","M","L","XL"};
        Integer [] type = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemopen.this, android.R.layout.simple_spinner_dropdown_item,size);
        spinner.setAdapter(adapter);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        username = sharedPreferences.getString("Username","");
        Request request = new Request(itemopen.this,"/api/headermenu", com.android.volley.Request.Method.GET) {
            @Override
            public void onresponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    ArrayList<String> data = new ArrayList<String>();
                    JSONArray redarray = new JSONArray();
                    for (int i=0;i<array.length();i++)
                    {
                        String value = array.getJSONObject(i).get("value").toString();
                        redarray.put(new JSONObject().put("category",value));
                        data.add(value);

                    }
                }
                catch (JSONException e)
                {

                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        };
        Bundle getid = getIntent().getExtras();
        String id = String.valueOf(getid.getInt("openitem"));
        Log.e("idd",id);
        getImage getImage= new getImage();
//      addtocart addtocart = new addtocart();
//      addtocart.execute(idd,username);
        getImage.execute();
        setdata setd = new setdata();
        setd.execute(id);
        matchid matchid = new matchid();
        matchid.execute(username);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
      Addtocart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
              username = sharedPreferences.getString("Username","");

              //if addtocart
              if (state){
                  Intent intent = new Intent(itemopen.this,whishlist.class);
                  Bundle data = new Bundle();
                  data.putString("sendid",idd);
                  intent.putExtras(data);
                  startActivity(intent);

              }
              else {
                  Intent intent = new Intent(itemopen.this, whishlist.class);
                  Bundle data = new Bundle();
                  data.putString("sendid", idd);
                  intent.putExtras(data);
                  productid productid = new productid();
                  productid.execute(username,idd);
              }
//              startActivity(intent);

//              Bundle data1 = new Bundle();
//              data.putString("backcheck","");
////              data.putString("sendid",idd);
//              intent.putExtras(data);
//              startActivity(intent);
          }
      });

    }
//    public class addtocart extends AsyncTask<String ,Void ,String>
//    {
//        int rs;
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//                helperdata = new Sqldatabase();
//                Connection con = helperdata.con();
//                if(con!=null)
//                {
//                    PreparedStatement preparedStatement = con.prepareStatement("insert into cart (prodid , username) values (?,?)");
//                    preparedStatement.setString(1,strings[0]);
//                    preparedStatement.setString(2,strings[1]);
//                    rs = preparedStatement.executeUpdate();
//                    if (rs >1)
//                    {
//                        return "success";
//                    }
//                    else
//                    {
//                        return "failed";
//                    }
//                }
//                else
//                {
//                    return "failed to connect";
//                }
//            }
//            catch (SQLException e)
//            {
//             e.printStackTrace();
//             e.getMessage();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            if (s.equals("succes"))
//            {
//             Log.e("sdsdfsafsa",String.valueOf(rs));
//            }
//        }
//    }
    public class productid extends AsyncTask<String , Void , String>
    {
        @Override
        protected String doInBackground(String... strings) {
            try {
                helperdata = new Sqldatabase();
                Connection con = helperdata.con();
                if (con !=null)
                {
                    PreparedStatement preparedStatement = con.prepareStatement("insert into cart (username , prodid) values (?,?)");
                    preparedStatement.setString(1,strings[0]);
                    preparedStatement.setString(2,strings[1]);
                    int rs = preparedStatement.executeUpdate();
                   if (rs > 0)
                   {
                       return "success";
                   }
                   else{
                       return "unsuccessfull";
                   }
                }
                else
                {
                   return "could not connect";
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
                Toast.makeText(itemopen.this, "added to cart", Toast.LENGTH_SHORT).show();
            }
        }
    }


//    public static void getImage(int id1)
//    {
//        setbigimage setbigimage1 =new setbigimage();
//        setbigimage1.execute(String.valueOf(id1));
//    }

//    public class setbigimage extends AsyncTask<String , Void ,String>
//    {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//
//                helperdata = new Sqldatabase();
//                Connection con = helperdata.con();
//
//                if (con != null) {
//                    PreparedStatement preparedStatement = con.prepareStatement("select image from items where id =?");
//                    preparedStatement.setString(1,strings[0]);
//                    ResultSet rs = preparedStatement.executeQuery();
//                    if (rs.isBeforeFirst())
//                    {
//                        while (rs.next())
//                        {
//
//                        }
//                        return "success";
//                    }
//                    else
//                    {
//                        return "failed";
//                    }
//                }
//                else
//                {
//                    return "could not connect";
//                }
//
//            }
//            return null;
//        }
//    }

    public class setdata extends AsyncTask<String ,Void ,String>
    {
//        ArrayList<String> category= new ArrayList<String>();
//        ArrayList<Integer> price = new ArrayList<Integer>();
//        ArrayList<String> item = new ArrayList<String>();
//        ArrayList<String> description = new ArrayList<String>();
//        ArrayList<String> type = new ArrayList<String>();
//        ArrayList<Integer> id = new ArrayList<Integer>();
//        ArrayList<String> image = new ArrayList<String>();
//        ArrayList<String> bank = new ArrayList<String>();
          List<String> pro = new ArrayList<String>();
//        ArrayList<String> discount = new ArrayList<String>();
        String category,item,description,type,image,bank,discount,product1,productdetails ,product2;
        int price,id;


        @Override
        protected String doInBackground(String... strings) {
            try {
                helperdata = new Sqldatabase();
                Connection con = helperdata.con();

                if (con != null) {
                    PreparedStatement preparedStatement = con.prepareStatement("select * from items where id =?");
                    preparedStatement.setString(1,strings[0]);
                    ResultSet rs = preparedStatement.executeQuery();
                    if (rs.isBeforeFirst())
                    {
                        while (rs.next())
                        {
                            category=(rs.getString(1));
                            price=(rs.getInt(2));
                            item=(rs.getString(3));
                            description=(rs.getString(4));
                            type=(rs.getString(5));
                            id=(rs.getInt(6));
                            image=(rs.getString(7));
                            bank=(rs.getString(8));
                            productdetails = (rs.getString(9));
                            pro= Arrays.asList(productdetails.split(", " ));
                            discount=(rs.getString(10));
                            idd = String.valueOf(id);

                        }
                        return "success";
                    }
                    else
                    {
                    return "failed";
                    }
                }
                else
                {
                 return "could not connect";
                }

            }
            catch (SQLException e)
            {
            e.getMessage();
            e.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(String s) {

            if (s.equals("success"))
            {
                topdescription.setText(category);
                price1.setText(String.valueOf(price)+"$");
                description1.setText(description);
                productdetails1.setText(productdetails);
              String url="http://103.209.65.222/TheStyleKlub/Images/Banner/";
              Glide.with(itemopen.this).asBitmap().load(url+image).into(new SimpleTarget<Bitmap>() {
                  @Override
                  public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                      bigpic.setImage(ImageSource.bitmap(resource));
                  }
              });
            }
            super.onPostExecute(s);
        }
    }
    public class getImage extends AsyncTask<String,Void ,String>
    {
        String category,item,description,type,image,bank,discount,product1,productdetails ,product2;
        int price;
        ArrayList<Integer> id = new ArrayList<Integer>();
        ArrayList<String> images= new ArrayList<>();
        @Override
        protected String doInBackground(String... strings) {
            try {
                helperdata = new Sqldatabase();
                Connection con = helperdata.con();
                if (con !=null)
                {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from items");
                    if (rs.isBeforeFirst())
                    {
                        while (rs.next())
                        {
                            category=(rs.getString(1));
                            price=(rs.getInt(2));
                            item=(rs.getString(3));
                            description=(rs.getString(4));
                            type=(rs.getString(5));
                            id.add(rs.getInt(6));
                            image=(rs.getString(7));
                            bank=(rs.getString(8));
//                          productdetails = (rs.getString(9));
//                          pro= Arrays.asList(productdetails.split(", " ));
                            discount=(rs.getString(10));
                            images.add(rs.getString(7));
                        }
                        return "success";
                    }
                    else
                    {
                        return "fail";
                    }

                }
                else
                {
                    return "Could not connect ";
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
                adapterpic = new picturesadapter(itemopen.this, images, id) {
                    @Override
                    public void getImage(String url) {
                        Glide.with(itemopen.this).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                bigpic.setImage(ImageSource.bitmap(resource));
                            }
                        });
                    }
                };
                recyclerView.setAdapter(adapterpic);
            }
        }
    }
    //TODO
    public class matchid extends AsyncTask<String , Void ,String >
    {
        ArrayList<Integer> id = new ArrayList<Integer>();
        @Override
        protected String doInBackground(String... strings) {
            try {
                helperdata = new Sqldatabase();
                Connection con = helperdata.con();
                if (con !=null)
                {
                    PreparedStatement preparedStatement = con.prepareStatement(" select distinct items.* from items join cart on items.id=cart.prodid where cart.username =? ");
                    preparedStatement.setString(1,strings[0]);
                    ResultSet rs = preparedStatement.executeQuery();
                    if (rs.isBeforeFirst())
                    {
                        while (rs.next())
                        {
                            id.add(rs.getInt(6));
                        }
                        return "success";
                    }
                    else
                    {
                        return "Failed";
                    }
                }
                else
                {
                    return "connection failed";
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
            for(int id2 :id)
            {
                if (String.valueOf(id2).equals(idd))
                {
                    Addtocart.setBackgroundColor(Color.parseColor("#CD4343"));
                    Addtocart.setText("Go To Cart");
                    state = true;
                }

            }
        }
        else
        {
            Log.e("color change","id printed");
        }
        }
    }

}