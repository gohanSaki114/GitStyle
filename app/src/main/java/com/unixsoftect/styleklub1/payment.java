package com.unixsoftect.styleklub1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class payment extends AppCompatActivity {
ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        viewPager = findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(payment.this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(adapter.getCount()-1);
    }
}