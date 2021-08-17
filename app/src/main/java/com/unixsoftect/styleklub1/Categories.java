package com.unixsoftect.styleklub1;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

public class Categories extends AppCompatActivity {


    ImageView back;
    BottomNavigationView bottomNavigationView;
    RecyclerView list;
    MaterialButton enter;
    String a="",b="";


    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.activity_categories, container, false);
//        back = root.findViewById(R.id.back);
//        enter = root.findViewById(R.id.enter);
//        list = root.findViewById(R.id.list_item3);
//                list.setAdapter(new CategoriesAdapter());
//        RecyclerView.LayoutManager manager = list.getLayoutManager();
////        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list.getContext(),new LinearLayoutManager(this).getOrientation());
////        list.addItemDecoration(dividerItemDecoration);
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
//        list.addItemDecoration(itemDecoration);
//
////        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(this, R.drawable.chat_list_bottom_bar));
////        list.addItemDecoration(dividerItemDecoration);
//
//
//
//        return root;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        back = findViewById(R.id.back);
        bottomNavigationView = findViewById(R.id.navigation);
        enter = findViewById(R.id.enter);
        list = findViewById(R.id.list_item3);
        Bundle extras1 = getIntent().getExtras();
        b = extras1.getString("backreturn");
        Bundle extras = getIntent().getExtras();
        a = extras.getString("backcheck");
        list.setAdapter(new CategoriesAdapter());
        RecyclerView.LayoutManager manager = list.getLayoutManager();
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list.getContext(),new LinearLayoutManager(this).getOrientation());
//        list.addItemDecoration(dividerItemDecoration);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(Categories.this,DividerItemDecoration.VERTICAL);
        list.addItemDecoration(itemDecoration);

//        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(this, R.drawable.chat_list_bottom_bar));
//        list.addItemDecoration(dividerItemDecoration);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.categories_item);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String categtory = "Category";
                Bundle data;
                switch (item.getItemId())
                {
                    case R.id.home_item:
                        Intent intent = new Intent(Categories.this,Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backcheck",categtory);
                        intent.putExtras(data);
                        startActivity(intent);
                        break;
                    case R.id.categories_item:

                        break;
                    case R.id.whishlist_item:
                        Intent intent1 = new Intent(Categories.this,whishlist.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backcheck",categtory);
                        intent1.putExtras(data);
                        startActivity(intent1);
                        break;
                        //
                    //
                    case R.id.profile_item:
                        Intent intent2 = new Intent(Categories.this,profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        data = new Bundle();
                        data.putString("backcheck",categtory);
                        intent2.putExtras(data);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {

if (!(a==null)||!(b==null))
{
    if (!(b==null))
    {
        Bundle data;
        if(!(b.isEmpty()))
        {
            switch (b) {
                case "home":
                    Intent intent = new Intent(Categories.this, Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    data = new Bundle();
                    data.putString("backreturn", b);
                    intent.putExtras(data);
                    startActivity(intent);
                    break;
                case "wish":
                    Intent intent1 = new Intent(Categories.this, whishlist.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    data = new Bundle();
                    data.putString("backreturn", b);
                    intent1.putExtras(data);
                    startActivity(intent1);
                    break;
                case "profile":
                    Intent intent2 = new Intent(Categories.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    data = new Bundle();
                    data.putString("backreturn", b);
                    intent2.putExtras(data);
                    startActivity(intent2);
                    break;
                case "Category":
                    finishAffinity();
                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + b);

            }
        }
      else  {

//            switch (b) {
//                case "home":
//                    Intent intent = new Intent(Categories.this, Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    data = new Bundle();
//                    data.putString("backreturn", a);
//                    intent.putExtras(data);
//                    startActivity(intent);
//                    break;
//                case "wish":
//                    Intent intent1 = new Intent(Categories.this, whishlist.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    data = new Bundle();
//                    data.putString("backreturn", a);
//                    intent1.putExtras(data);
//                    startActivity(intent1);
//                    break;
//                case "profile":
//                    Intent intent2 = new Intent(Categories.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    data = new Bundle();
//                    data.putString("backreturn", a);
//                    intent2.putExtras(data);
//                    startActivity(intent2);
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + a);
//
//            }
        }
    }
    else if(!(a==null))
    {
        Bundle data;
        if (!(a.isEmpty())) {
            switch (a) {
                case "home":
                    Intent intent = new Intent(Categories.this, Drawer.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    data = new Bundle();
                    data.putString("backreturn", a);
                    intent.putExtras(data);
                    startActivity(intent);
                    break;
                case "profile":
                    Intent intent1 = new Intent(Categories.this, profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    data = new Bundle();
                    Log.e("gsafahbb",a);
                    data.putString("backreturn", a);
                    intent1.putExtras(data);
                    startActivity(intent1);
                    break;
                case "wish":
                    Intent intent2 = new Intent(Categories.this, whishlist.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
    else
    {
        Log.e("shgjshdjihsji","returned");
    }
}
    }

//    static class DividerItemDecorator extends RecyclerView.ItemDecoration {
//
//        private final Drawable mDivider;
//        private final Rect mBounds = new Rect();
//
//        public DividerItemDecorator(Drawable divider) {
//            mDivider = divider;
//        }
//
//        @Override
//        public void onDraw(Canvas canvas, RecyclerView parent, @NotNull RecyclerView.State state) {
//            canvas.save();
//            final int left;
//            final int right;
//            if (parent.getClipToPadding()) {
//                left = parent.getPaddingLeft();
//                right = parent.getWidth() - parent.getPaddingRight();
//                canvas.clipRect(left, parent.getPaddingTop(), right,
//                        parent.getHeight() - parent.getPaddingBottom());
//            } else {
//                right = parent.getWidth();
//            }
//
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount - 1; i++) {
//                final View child = parent.getChildAt(i);
//                parent.getDecoratedBoundsWithMargins(child, mBounds);
//                final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
//                final int top = bottom - mDivider.getIntrinsicHeight();
//                mDivider.setBounds(50, top, right, bottom);
//                mDivider.draw(canvas);
//            }
//            canvas.restore();
//        }
//
//        @Override
//        public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, RecyclerView parent, RecyclerView.State state) {
//
//            if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
//                outRect.setEmpty();
//            } else
//                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
//        }
//    }
}