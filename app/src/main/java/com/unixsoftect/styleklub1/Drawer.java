package com.unixsoftect.styleklub1;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.android.volley.VolleyError;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
public class Drawer extends AppCompatActivity {
    TextView header1, header2;
    //BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPreferences;
    ExpandableListView expandableListView;
    //    List<String> menu1=new ArrayList<>(), category1=new ArrayList<>(), subcategory1=new ArrayList<>();
//    List<headermodal> headerlist = new ArrayList<headermodal>();
//    HashMap<headermodal, List<headermodal>> childlist = new HashMap<>();
    Menus menus;
    DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        expandableListView = findViewById(R.id.expandedlist);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        header1 = headerView.findViewById(R.id.nameheader);
      //bottomNavigationView = findViewById(R.id.navigation);
        header1.setText(sharedPreferences.getString("Username", ""));
        header2 = headerView.findViewById(R.id.userheader);
        header2.setText(sharedPreferences.getString("Name", ""));
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//        navigationView.setNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.Categories:
//                    Intent intent = new Intent(Drawer.this, Categories.class);
//                    startActivity(intent);
//                    break;
//            }
//            drawer.closeDrawer(GravityCompat.START);
//            return true;
//        });
         new Request(Drawer.this, "/api/headermenu", com.android.volley.Request.Method.GET) {
            @Override
            public void onresponse(String response) {
                try {
                    menus = new Menus(response);
                    prepareData();
                } catch (Exception e) {
                    Log.e(TAG, "onresponse: Error" + e);
                }
                // Log.e(TAG, "onresponse: "+response );
                try {
                    //menus = new Menus(response);
//                    List<levelmodel> menu = new ArrayList<levelmodel>();
//                    Integer[] dd = new Integer[3];
//                    dd[0] = R.drawable.ic_baseline_home_24;
//                    dd[1] = R.drawable.ic_baseline_male_24;
//                    dd[2] = R.drawable.ic_baseline_female_24;
//                    List<String> menu1 = menus.getMenu();
//                    for (int i = 0; i < menu1.size(); i++) {
//                        levelmodel model = new levelmodel(dd[i], menu1.get(i));
//                        menu.add(model);
//                        Log.e("menu gol",menu.get(i).getTitle());
//                        Log.e("menu gol",menu.get(i).getIcon().toString());
//                    }


//                    preparemenudata();
                    //populate_menu_data();
//                  Log.e(TAG, "onresponse: " + menus.getCategory(menu.get(1)).toString());
                }
//                catch (JSONException e) {
//                    Log.e(TAG, "onresponse:EXCEPTION "+e );
//
//                    e.printStackTrace();
//                }
                catch (Exception e) {
                    Log.e(TAG, "onresponse:EXCEPTION " + e);
                }
            }
            @Override
            public void onError(VolleyError error) {
                Log.e(TAG, "onError: " + error);
            }
        };
    }

    private void prepareData() throws Exception {
        List<String> menu, category, subcategory;
        menu = menus.getMenu();
        List<levelmodel> menulevelmodel = new ArrayList<>();
        List<List<levelmodel>> categorylevelmodel = new ArrayList<>();
        List<LinkedHashMap<String, List<levelmodel>>> data = new ArrayList<>();
        //icons
        Integer[] images = new Integer[3];
        images[0] = R.drawable.ic_baseline_home_24;
        images[1] = R.drawable.ic_baseline_male_24;
        images[2] = R.drawable.ic_baseline_female_24;
        //has children
        boolean[] haschild = new boolean[3];
        haschild[0] = false;
        haschild[1] = true;
        haschild[2] = true;

        for (int x = 0; x < menu.size(); x++) {
            levelmodel levelmodel = new levelmodel(images[x], menu.get(x), haschild[x]);
            menulevelmodel.add(levelmodel);
        }
        for (int i = 0; i < menu.size(); i++) {
            category = menus.getCategory(menu.get(i));
            LinkedHashMap<String, List<levelmodel>> thirdLevelData = new LinkedHashMap<>();
            if (category.size() > 0) {
                List<levelmodel> categorylevelmodellist = new ArrayList<>();
                for (int y = 0; y < category.size(); y++) {
                    levelmodel levelmodel = new levelmodel(images[0], category.get(y));
                    categorylevelmodellist.add(levelmodel);
                    Log.e("category list", category.toString());
                }
                categorylevelmodel.add(categorylevelmodellist);
                for (int j = 0; j < category.size(); j++) {
                    subcategory = menus.getSubCategory(menu.get(i), category.get(j));
                    List<levelmodel> subcategorylevelmodellist = new ArrayList<>();
                    Log.e("show list", subcategory.toString());
                    for (int z = 0; z < subcategory.size(); z++) {
                        levelmodel levelmodel = new levelmodel(images[0], subcategory.get(z));
                        subcategorylevelmodellist.add(levelmodel);
                        thirdLevelData.put(category.get(j), subcategorylevelmodellist);
                    }
                }
                data.add(thirdLevelData);
            } else {
                categorylevelmodel.add(new ArrayList<>());
                thirdLevelData.put("", new ArrayList<>());
                data.add(new LinkedHashMap<>());
            }
        }
        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(Drawer.this, menulevelmodel, categorylevelmodel, data);   // set adapter
        expandableListView.setAdapter(threeLevelListAdapterAdapter);
        // OPTIONAL : Show one list at a time

//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            int previousGroup = -1;
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                if (groupPosition != previousGroup)
//                    expandableListView.collapseGroup(previousGroup);
//                previousGroup = groupPosition;
//            }
//        });
    }
    //prepare method
//    private void preparemenudata() throws Exception {
//        menu1=menus.getMenu();
//        if (menu1.size() > 0) {
//            Integer[] images = new Integer[3];
//            images[0] = R.drawable.ic_baseline_home_24;
//            images[1] = R.drawable.ic_baseline_male_24;
//            images[2] = R.drawable.ic_baseline_female_24;
////            List<String> menu1 = menus.getMenu();
//            for (int i = 0; i < menu1.size(); i++) {
//                levelmodel levelmodel=new levelmodel(images[i],menu1.get(i));
//                menu.add(levelmodel);
//                category1=menus.getCategory(menu1.get(i));
//                List<levelmodel> levelmodelList=new ArrayList<>();
//                if(category1.size()>0){
//
//                    for (int j = 0; j < category1.size(); j++) {
//                        levelmodel levelmodel1=new levelmodel(images[i],category1.get(j));
//                        levelmodelList.add(levelmodel1);
//                        subcategory1=menus.getSubCategory(menu1.get(i),category1.get(j));
//                        LinkedHashMap<String, List<levelmodel>> linkedHashMap=new LinkedHashMap<>();
//                        List<levelmodel> levelmodelList1=new ArrayList<>();
//                        if(subcategory1.size()>0){
//                            for (int k = 0; k < subcategory1.size(); k++) {
//                                levelmodel levelmodel2=new levelmodel(images[i],subcategory1.get(k));
//                                levelmodelList1.add(levelmodel2);
//                            }
//                            linkedHashMap.put(category1.get(j),levelmodelList1);
//                            data.add(linkedHashMap);
//                        }
//                        else data.add(new LinkedHashMap<>());
//
//                    }
//                    secondLevel.add(levelmodelList);
//                }
//                else
//                    secondLevel.add(new ArrayList<>());
//
////                ArrayList<String> takethat = new ArrayList<String>();
////                takethat.add(menus.getCategory());
////                levelmodel modal = new levelmodel(images[i],takethat.get(i));
////                category.add(modal);
////                category1 = menus.getCategory(menu1.get(i));
////                if (category1.size() > 0) {
////                    for (int k = 0; k < category1.size(); k++) {
////                        levelmodel modal = new levelmodel(images[k], category1.get(k));
////                        category.add(modal);
////                        for (int j = 0; j < category1.size(); j++) {
////                            subcategory1 = menus.getSubCategory(menu1.get(i), category1.get(k));
////                            if(subcategory1.size()>0){
////                                for (int l = 0; l < subcategory1.size(); l++) {
////                                    levelmodel levelmodel=new levelmodel(images[0],subcategory1.get(l));
////                                    subcategory.add(levelmodel);
////                                }
////                            }else{
////                                subcategory.add(new levelmodel());
////                            }
////                        }
////                    }
////                } else {
////                    category.add(new levelmodel());
////                }
////
//////                boolean hasChildren = category.size() > 0;
//////                boolean isGroup = category.size()>0;
//////                headermodal modal = new headermodal(menu.get(i), ContextCompat.getDrawable(this, dd[i<3?i:0]), isGroup, hasChildren);
//////                headerlist.add(modal);
//////                  String[] parent = new String[menu.size()];
//////                  menu.toArray(parent);
////                secondLevel.add(category);
//////                    List<headermodal> childModelList = new ArrayList<>();
//////                    for (int j = 0; j < category.size(); j++) {
//////                        headermodal childModel = new headermodal(category.get(j), null, false, false);
//////                        childModelList.add(childModel);
//////                        subcategory=menus.getSubCategory(menu.get(i),category.get(j));
////                if (subcategory.size() > 0) {
//////                         data.add(category.get(j),subcategory);
//////                         second.put(category.get(j),subcategory);
////                    data.add(second);
////                }
//////                    }
//////                    childlist.put(modal,childModelList);
////
//////                    secondLevel.add(new ArrayList<>());
//////                    childlist.put(modal, null);
//
//            }
//            ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, menu, secondLevel, data);
//            expandableListView.setAdapter(threeLevelListAdapterAdapter);
//            expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//                int previousGroup = -1;
//
//                @Override
//                public void onGroupExpand(int groupPosition) {
//                    if (groupPosition != previousGroup)
//                        expandableListView.collapseGroup(previousGroup);
//                    previousGroup = groupPosition;
//                }
//            });
//        }
////        Log.e("headerdara5", menu.get(0));
////        headermodal modal = new headermodal(menu.get(0), ContextCompat.getDrawable(this, R.drawable.ic_baseline_home_24), false, false);
////        headerlist.add(modal);
////        if (!modal.hasChildren) {
////            childlist.put(modal, null);
////        }
////        modal = new headermodal(menu.get(1), ContextCompat.getDrawable(this, R.drawable.ic_baseline_male_24), true, true);
////        headerlist.add(modal);
////        List<headermodal> childlistmodal = new ArrayList<headermodal>();
////        headermodal childmodal = new headermodal(category.get(0), null, true, true);
////        childlistmodal.add(childmodal);
////        childmodal = new headermodal(category.get(1), null, true, true);
////        childlistmodal.add(childmodal);
////        childmodal = new headermodal(category.get(2), null, true, true);
////        childlistmodal.add(childmodal);
////        if (modal.hasChildren) {
////            childlist.put(modal, childlistmodal);
////        }
////        modal = new headermodal(menu.get(2), ContextCompat.getDrawable(this, R.drawable.ic_baseline_female_24), true, true);
////        headerlist.add(modal);
////        List<headermodal> childlistmodal1 = new ArrayList<headermodal>();
////        headermodal childmodal1 = new headermodal(category.get(0), null, true, true);
////        childlistmodal1.add(childmodal1);
////        childmodal1 = new headermodal(category.get(1), null, true, true);
////        childlistmodal1.add(childmodal1);
////        childmodal1 = new headermodal(category.get(2), null, true, true);
////        childlistmodal1.add(childmodal1);
////        if (modal.hasChildren) {
////            childlist.put(modal, childlistmodal1);
////        }
//    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
    //    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
// }
    private void populate_menu_data() {
//        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(this, headerlist, childlist);
//        expandableListView.setAdapter(expandableListAdapter);
//        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
//            if (childlist.get(headerlist.get(groupPosition)) != null) {
//                headermodal model = Objects.requireNonNull(childlist.get(headerlist.get(groupPosition))).get(childPosition);

//                switch (model.menuName) {
//                    case "View Post":
//                        startActivity(new Intent(Drawer.this, ViewPosts.class)
//                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                        break;
//                    case "Add Post":
//                        startActivity(new Intent(Dashboard.this, MyAds.class).putExtra("index", 0)
//                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                        break;
//                    case "My Added Post":
//                        startActivity(new Intent(Dashboard.this, MyAds.class).putExtra("index", 1)
//                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                        break;
//                }


//           }
//            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
//            drawerLayout.closeDrawer(GravityCompat.START);
//            return false;
//        });
//        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
//
//            if (!headerlist.get(groupPosition).isGroup) {
//
//                if (headerlist.get(groupPosition).menuName.equals("About")) {
//                    startActivity(new Intent(Dashboard.this, About.class)
//                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                }
//                if (headerList.get(groupPosition).menuName.equals("Terms & Conditions")) {
//                    startActivity(new Intent(Dashboard.this, Terms_and_Conditions.class)
//                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                }
//                if (headerList.get(groupPosition).menuName.equals("Broker List")) {
//                    startActivity(new Intent(Dashboard.this, GetListBroker_Booking.class).putExtra("type", "Broker")
//                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                }
//                if (headerList.get(groupPosition).menuName.equals("Booking List")) {
//                    startActivity(new Intent(Dashboard.this, GetListBroker_Booking.class).putExtra("type", "Booking")
//                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                }
//                if (headerList.get(groupPosition).menuName.equals("Chats")) {
//                    startActivity(new Intent(Dashboard.this, ChatList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
//                }
//                if (headerList.get(groupPosition).menuName.equals("My Ads")) {
//                    return false;
//                }
//            } else {
//                return false;
//            }
//            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
//            drawerLayout.closeDrawer(GravityCompat.START);
//            return false;
//        });
    }
}
//<shape
//    xmlns:android="http://schemas.android.com/apk/res/android"
//            android:shape="rectangle"   >
//
//<solid
//        android:color="#eeffffff" >
//</solid>
//
//<corners
//        android:bottomRightRadius="8dp"
//                android:bottomLeftRadius="8dp"
//                android:topRightRadius="8dp"
//                android:topLeftRadius="8dp"
//                />
//</shape>