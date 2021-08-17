package com.unixsoftect.styleklub1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class profile extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView user, email;
    ImageView userimage, back;
    SharedPreferences sharedPreferences;
    String a = "", b = "";
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        back = findViewById(R.id.back1);
        userimage = findViewById(R.id.circle);
        user = findViewById(R.id.user);
        email = findViewById(R.id.email);
        Bundle extras1 = getIntent().getExtras();
        b = extras1.getString("backreturn");
        Bundle extras = getIntent().getExtras();
        a = extras.getString("backcheck");
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        email.setText(sharedPreferences.getString("Username", ""));
        user.setText(sharedPreferences.getString("Name", ""));
        Glide.with(profile.this).load(R.drawable.shoe).apply(new RequestOptions().override(230, 230)).circleCrop().into(userimage);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile_item);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String profile = "profile";
                Bundle data;
                switch (item.getItemId()) {
                    case R.id.home_item:
                        Intent intent = new Intent(profile.this, Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backcheck", profile);
                        intent.putExtras(data);
                        startActivity(intent);
                        break;
                    case R.id.categories_item:
                        Intent intent1 = new Intent(profile.this, Categories.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backcheck", profile);
                        intent1.putExtras(data);
                        startActivity(intent1);
                        break;
                    case R.id.whishlist_item:
                        Intent intent2 = new Intent(profile.this, whishlist.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backcheck", profile);
                        intent2.putExtras(data);
                        startActivity(intent2);
                        break;
                    case R.id.profile_item:
                        break;
                }
                return true;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.super.onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Bundle data;
        if (!(a == null) || !(b == null)) {
            if (!(a==null))
            {
            if (!(a.isEmpty())) {
                switch (a) {
                    case "home":
                        Intent intent = new Intent(profile.this, Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backreturn", a);
                        intent.putExtras(data);
                        startActivity(intent);
                        break;
                    case "Category":
                        Intent intent1 = new Intent(profile.this, Categories.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        Log.e("gsafahbb", a);
                        data.putString("backreturn", a);
                        intent1.putExtras(data);
                        startActivity(intent1);
                        break;
                    case "wish":
                        Intent intent2 = new Intent(profile.this, whishlist.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backreturn", a);
                        intent2.putExtras(data);
                        startActivity(intent2);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + a);
                }
            }
//            else if (b.length() != 0) {
//                finishAffinity();
//            switch (a) {
//                case "home":
//                    Intent intent = new Intent(profile.this, Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    data = new Bundle();
//                    data.putString("backreturn", a);
//                    intent.putExtras(data);
//                    startActivity(intent);
//                    break;
//                case "Category":
//                    Intent intent1 = new Intent(profile.this, Categories.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    data = new Bundle();
//                    data.putString("backreturn", a);
//                    intent1.putExtras(data);
//                    startActivity(intent1);
//                    Log.e("On backpressed",""+a);
//                    break;
//                case "wish":
//                    Intent intent2 = new Intent(profile.this, whishlist.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    data = new Bundle();
//                    data.putString("backreturn", a);
//                    intent2.putExtras(data);
//                    startActivity(intent2);
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + a);
//
//            }
//            }
            }
            else if(!(b==null))
            {
                if (!(b.isEmpty())) {
                    switch (b) {
                        case "home":
                            Intent intent = new Intent(profile.this, Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            data = new Bundle();
                            data.putString("backreturn",b);
                            intent.putExtras(data);
                            startActivity(intent);
                            break;
                        case "Category":
                            Intent intent1 = new Intent(profile.this, Categories.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            data = new Bundle();
                            Log.e("gsafahbb",b);
                            data.putString("backreturn", b);
                            intent1.putExtras(data);
                            startActivity(intent1);
                            break;
                        case "wish":
                            Intent intent2 = new Intent(profile.this, whishlist.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            data = new Bundle();
                            data.putString("backreturn", b);
                            intent2.putExtras(data);
                            startActivity(intent2);
                            break;
                        case "profile":
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
    }
}