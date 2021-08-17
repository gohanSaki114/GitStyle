package com.unixsoftect.styleklub1.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.unixsoftect.styleklub1.Categories;
import com.unixsoftect.styleklub1.MainSliderAdapter;
import com.unixsoftect.styleklub1.PicassoImageLoadingService;
import com.unixsoftect.styleklub1.R;
import com.unixsoftect.styleklub1.Request;
import com.unixsoftect.styleklub1.Sqldatabase;
import com.unixsoftect.styleklub1.itemadapter;
import com.unixsoftect.styleklub1.modals.mainmodal;
import com.unixsoftect.styleklub1.profile;
import com.unixsoftect.styleklub1.whishlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class HomeFragment extends Fragment {
    Context context;
    Slider slider;
    SearchView searchView;
    itemadapter itemadapter1, itemadapter2;
    View include;
    RecyclerView listitem2, listitem;
    mainmodal modal;
    Sqldatabase sqldatabase;
    BottomNavigationView navigationView;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, seeall1, seeall2;
    private HomeViewModel homeViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView1 = root.findViewById(R.id.tshirt);
        include = root.findViewById(R.id.incnavigation);
        textView2 = root.findViewById(R.id.jeans);
        textView4  = root.findViewById(R.id.next1);
        slider = root.findViewById(R.id.bannerslider);
        navigationView = root.findViewById(R.id.navigation);
        textView3 = root.findViewById(R.id.blazers);
        seeall1 = root.findViewById(R.id.seeall1);
        seeall2 = root.findViewById(R.id.seeall2);
        searchView = root.findViewById(R.id.search);
        searchView.setIconifiedByDefault(false);
        this.context = container.getContext();
        listitem = root.findViewById(R.id.list_item);
        listitem2 = root.findViewById(R.id.list_item2);
        PicassoImageLoadingService loadingService = new PicassoImageLoadingService(context);
        //listitem2.setAdapter(itemadapter);
//        listitem.setAdapter(itemadapter);
        Slider.init(loadingService);
        bringdata2 bring2 = new bringdata2();
        bring2.execute("collection");
        bringdata bring = new bringdata();
        bring.execute();
         new Request(getActivity(),"/api/subcategory", com.android.volley.Request.Method.GET) {
            @Override
            public void onresponse(String response) {
                try {
                    //JSONArray if the data is in bunch & JSONObject if data is less
                    JSONArray array = new JSONArray(response);
                    ArrayList<String> data = new ArrayList<String>();
                    JSONArray reducearray = new JSONArray();
                    for (int i=0;i<array.length();i++)
                    {
                        String value = array.getJSONObject(i).get("SubCategoryName").toString();
                        reducearray.put(new JSONObject().put("SubCategoryName",value));

                    }
                    for (int j=0;j<reducearray.length();j++)
                    {
                        String var = reducearray.getJSONObject(j).get("SubCategoryName").toString();
                        data.add(var);
                    }
                    textView1.setText(data.get(0));
                    textView2.setText(data.get(1));
                    textView3.setText(data.get(2));
                    textView4.setText(data.get(3));
                }
            catch(JSONException e)
                {
                 e.printStackTrace();
                 e.getMessage();
                }
            }
            @Override
            public void onError(VolleyError error) {
            }
        };
        slider.setAdapter(new MainSliderAdapter());
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        seeall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeall1.setTextColor(Color.parseColor("#194281"));
                itemadapter2.seeall1 = true;
                itemadapter2.notifyDataSetChanged();
            }
        });
        seeall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeall2.setTextColor(Color.parseColor("#194281"));
                itemadapter1.seeall1 = true;
                itemadapter1.notifyDataSetChanged();
            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String home = "home";
                Bundle data;
                switch (item.getItemId()) {
                    case R.id.home_item:
                        Categories categories = new Categories();
//                        FragmentManager manager = getFragmentManager();
//                        FragmentTransaction transaction = manager.beginTransaction();
//                        transaction.add(R.id.framelayout,categories,"frag_tag");
//                        transaction.commit();
                        break;
                    case R.id.categories_item:
                        Intent intent = new Intent(context, Categories.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backcheck", home);
                        intent.putExtras(data);
                        startActivity(intent);
                        break;
                    case R.id.whishlist_item:
                        Intent intent1 = new Intent(context, whishlist.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backcheck", home);
                        intent1.putExtras(data);
                        startActivity(intent1);
                        break;
                    case R.id.profile_item:
                        Intent intent2 = new Intent(context, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backcheck", home);
                        intent2.putExtras(data);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });

//        Handler handler1 = new Handler();
//        handler1.post(new Runnable() {
//            @Override
//            public void run() {
//                handler1.postDelayed(this, 1);
//            }
//        });
        return root;
    }
    class bringdata extends AsyncTask<String, Void, String> {
        List<String> category = new ArrayList<String>();
        List<Integer> price = new ArrayList<Integer>();
        List<String> item = new ArrayList<String>();
        List<String> description = new ArrayList<String>();
        List<String> type = new ArrayList<String>();
        List<Integer> id = new ArrayList<Integer>();
        List<String> image = new ArrayList<String>();

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                sqldatabase = new Sqldatabase();
                Connection con = sqldatabase.con();
                if (con != null) {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from items");
                    if (rs.isBeforeFirst()) {
                        while (rs.next()) {
                            category.add(rs.getString(1));
                            price.add(rs.getInt(2));
                            item.add(rs.getString(3));
                            description.add(rs.getString(4));
                            type.add(rs.getString(5));
                            id.add(rs.getInt(6));
                            image.add(rs.getString(7));
                        }
                        return "success";
                    } else {
                        return "fail";
                    }
                } else {
                    return "Could not connect ";
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("success")) {
                modal = new mainmodal(category, price, item, description, type, id, image);
                itemadapter1 = new itemadapter(getActivity(), modal);
                listitem2.setAdapter(itemadapter1);
            }
        }
    }
    class bringdata2 extends AsyncTask<String, Void, String> {
        List<String> category = new ArrayList<String>();
        List<Integer> price = new ArrayList<Integer>();
        List<String> item = new ArrayList<String>();
        List<String> description = new ArrayList<String>();
        List<String> type = new ArrayList<String>();
        List<Integer> id = new ArrayList<Integer>();
        List<String> image = new ArrayList<String>();

        @Override
        protected String doInBackground(String... strings) {
            try {
                sqldatabase = new Sqldatabase();
                Connection con = sqldatabase.con();
                if (con != null) {
                    PreparedStatement preparedStatement = con.prepareStatement("select * from items where category = ?");
                    preparedStatement.setString(1, strings[0]);
                    ResultSet rs = preparedStatement.executeQuery();
                    if (rs.isBeforeFirst()) {
                        while (rs.next()) {
                            category.add(rs.getString(1));
                            price.add(rs.getInt(2));
                            item.add(rs.getString(3));
                            description.add(rs.getString(4));
                            type.add(rs.getString(5));
                            id.add(rs.getInt(6));
                            image.add(rs.getString(7));
                        }
                        return "success";
                    } else {
                        return "fail";
                    }
                } else {
                    return "could not connect";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("success")) {
                modal = new mainmodal(category, price, item, description, type, id, image);
                itemadapter2 = new itemadapter(getActivity(), modal);
                listitem.setAdapter(itemadapter2);
            }
        }
    }
}
//"/api/subcategory"