package com.unixsoftect.styleklub1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.AsyncPlayer;
import android.media.audiofx.PresetReverb;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class whishlist extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView recycler;
    ImageView back;
    SharedPreferences sharedPreferences;
    Sqldatabase helperdata;
    whishlistadapter whishadapter;
    String a ="",b="";
    String idd ="";
    whishlistmodal modal;
    String username ="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whishlist);
        bottomNavigationView = findViewById(R.id.navigation);
        recycler = findViewById(R.id.list_item3);
        back = findViewById(R.id.back1);
        Bundle extras1 = getIntent().getExtras();
        b = extras1.getString("backreturn");
        Bundle extras = getIntent().getExtras();
        a = extras.getString("backcheck");
        Bundle extras2 = getIntent().getExtras();
        idd = extras2.getString("sendid");
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        username = sharedPreferences.getString("Username","");
//        idusername idusername = new idusername();
//        idusername.execute(username);
        getitem getitem = new getitem();
        getitem.execute(username);

        //Back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        databyid databyid = new databyid();
//        databyid.execute(idd);
        //Bottom Navigation
        bottomNavigationView.setSelectedItemId(R.id.whishlist_item);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String wish = "wish";
                Bundle data;
                switch(item.getItemId())
                {
                    case R.id.home_item:
                       Intent intent = new Intent(whishlist.this,Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       data = new Bundle();
                       data.putString("backcheck",wish);
                       intent.putExtras(data);
                       startActivity(intent);
                        break;
                    case R.id.categories_item:
                        Intent intent1 = new Intent(whishlist.this,Categories.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                         data = new Bundle();
                        data.putString("backcheck",wish);
                        intent1.putExtras(data);
                        startActivity(intent1);
                        break;
                    case R.id.whishlist_item:

                        break;
                    case R.id.profile_item:
                        Intent intent2 = new Intent(whishlist.this,profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backcheck",wish);
                        intent2.putExtras(data);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });

    }



//    public class databyid extends AsyncTask<String , Void , String>
//    {
//        ArrayList<String> id = new ArrayList<String>();
//        ArrayList<String> image = new ArrayList<String>();
//        ArrayList<String> item = new ArrayList<String>();
//        ArrayList<String> price = new ArrayList<String>();
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//                helperdata = new Sqldatabase();
//                Connection con = helperdata.con();
//                if (con !=null)
//                {
//                    PreparedStatement preparedStatement = con.prepareStatement("select * from items where id =? ");
//                    preparedStatement.setString(1,strings[0]);
//                    ResultSet rs = preparedStatement.executeQuery();
//                    if (rs.isBeforeFirst())
//                    {
//                        while(rs.next())
//                        {
//                         id.add(rs.getString(6));
//                         item.add(rs.getString(3));
//                         image.add(rs.getString(7));
//                         price.add(rs.getString(2));
//                        }
//                        return "success";
//                    }
//                    else
//                    {
//                        return "failed";
//                    }
//                }
//            }
//            catch (SQLException e)
//            {
//             e.getMessage();
//             e.printStackTrace();
//            }
//            return null;
//        }
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
////            Log.e("Success ",s);
////            Log.e("ID",idd);
//            if (s.equals("success"))
//            {
//
//                modal = new whishlistmodal(whishlist.this,item,image,price);
//                whishadapter = new whishlistadapter(whishlist.this,modal);
//                recycler.setAdapter(whishadapter);
//                DividerItemDecoration itemDecoration = new DividerItemDecoration(whishlist.this,DividerItemDecoration.VERTICAL);
//                recycler.addItemDecoration(itemDecoration);
//            }
//        }
//    }
//    public class idusername extends AsyncTask<String , Void , String>
//    {
//        String username1;
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//                helperdata = new Sqldatabase();
//                Connection con = helperdata.con();
//                if (con != null)
//                {
//                    PreparedStatement preparedStatement = con.prepareStatement("select * from cart where  username =?");
//                    preparedStatement.setString(1,strings[0]);
//                    preparedStatement.setString(2,strings[1]);
//                    ResultSet rs = preparedStatement.executeQuery();
//                    if (rs.isBeforeFirst())
//                    {
//                        while(rs.next())
//                        {
//                        this.username1=rs.getString(1);
//
//                        }
//                        return "success";
//                    }
//                }
//                else
//                {
//                    return "failed to connect";
//                }
//            }
//            catch(SQLException e)
//            {
//                e.printStackTrace();
//                e.getMessage();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            if(s.equals("success"))
//            {
//               Log.e("saasdf",username1);
//            }
//        }
//    }
    public class getitem extends AsyncTask<String ,Void ,String>
    {
ArrayList<String> image = new ArrayList<String>();
ArrayList<String> item = new ArrayList<String>();
ArrayList<Integer> price = new ArrayList<Integer>();
ArrayList<Integer> id = new ArrayList<Integer>();
ArrayList<String> size = new ArrayList<String>();
ArrayList<String> quantity = new ArrayList<String>();
        @Override
        protected String doInBackground(String... strings) {
            try {
                helperdata = new Sqldatabase();
                Connection con = helperdata.con();
                if (con !=null)
                {
                    PreparedStatement preparedStatement = con.prepareStatement("select distinct * from items join cart on items.id=cart.prodid where cart.username =  ?");
                    preparedStatement.setString(1,strings[0]);
                    ResultSet rs = preparedStatement.executeQuery();
                    if (rs.isBeforeFirst())
                    {
                        while (rs.next())
                        {
                         price.add(rs.getInt(2));
                         item.add(rs.getString(3));
                         image.add(rs.getString(7));
                         id.add(rs.getInt(6));
                         size.add(rs.getString(15));
                         quantity.add(rs.getString(16));
                        }
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
                modal = new whishlistmodal(whishlist.this, item, image, price,id,size,quantity);
                whishlistadapter adapter = new whishlistadapter(whishlist.this, modal);
                recycler.setAdapter(adapter);
                Log.e("success ",s);
            }
            else
            {
                Log.e("failer",s);
            }
        }
    }
//    public class Bringdata extends AsyncTask<String ,Void ,String>
//    {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//            helperdata = new Sqldatabase();
//            Connection con = helperdata.con();
//            if (con != null)
//            {
//                PreparedStatement
//            }
//            else
//                {
//                    return "connection failed";
//                }
//
//            return null;
//        }
//            catch (SQLException e)
//            {
//            e.getMessage();
//            e.printStackTrace();
//            }
//        }
//
//    }

    @Override
    public void onBackPressed() {
        Bundle data;
        if (!(a == null) || !(b == null)) {
            if (!(a==null))
            {
                if (!(a.isEmpty())) {
                    switch (a) {
                        case "home":
                            Intent intent = new Intent(whishlist.this, Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            data = new Bundle();
                            data.putString("backreturn", a);
                            intent.putExtras(data);
                            startActivity(intent);
                            break;
                        case "Category":
                            Intent intent1 = new Intent(whishlist.this, Categories.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            data = new Bundle();
                            Log.e("gsafahbb", a);
                            data.putString("backreturn", a);
                            intent1.putExtras(data);
                            startActivity(intent1);
                            break;
                        case "profile":
                            Intent intent2 = new Intent(whishlist.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            data = new Bundle();
                            data.putString("backreturn", a);
                            intent2.putExtras(data);
                            startActivity(intent2);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + a);
                    }
                }
            }
            else if(!(b==null))
            {
                if (!(b.isEmpty())) {
                    switch (b) {
                        case "home":
                            Intent intent = new Intent(whishlist.this, Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            data = new Bundle();
                            data.putString("backreturn",b);
                            intent.putExtras(data);
                            startActivity(intent);
                            break;
                        case "Category":
                            Intent intent1 = new Intent(whishlist.this, Categories.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            data = new Bundle();
                            Log.e("gsafahbb",b);
                            data.putString("backreturn", b);
                            intent1.putExtras(data);
                            startActivity(intent1);
                            break;
                        case "profile":
                            Intent intent2 = new Intent(whishlist.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            data = new Bundle();
                            data.putString("backreturn", b);
                            intent2.putExtras(data);
                            startActivity(intent2);
                            break;
                        case "wish":
                            finishAffinity();
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + b);
                    }
                }

            }
            else
            {

                Log.e("bjkhhsfhsb","returned");
            }
        }
        else if ((a==null)||(b==null))
            {
              super.onBackPressed();
            }
        else
        {
            Log.e("bjkhhsfhsb","successfull return");
        }
    }
    }
    //        @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }


// select distinct items.* from items join cart on items.id=cart.prodid
// select distinct  * from items join cart on items.id=cart.prodid