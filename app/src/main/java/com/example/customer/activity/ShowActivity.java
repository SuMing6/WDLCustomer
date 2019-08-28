package com.example.customer.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.example.customer.R;
import com.example.customer.fragment.HomePageFragment;
import com.example.customer.fragment.MyFragment;
import com.example.customer.fragment.OrderFragment;
import com.example.customer.fragment.ShoppingFragment;

public class ShowActivity extends AppCompatActivity {

    private HomePageFragment homePageFragment;
    private ShoppingFragment shoppingFragment;
    private OrderFragment orderFragment;
    private MyFragment myFragment;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_show);
        //页面展示
        show();


    }

    private void show() {
        RadioGroup radioGroup = findViewById(R.id.show_RadioGroup);

        homePageFragment = new HomePageFragment();
        shoppingFragment = new ShoppingFragment();
        orderFragment = new OrderFragment();
        myFragment = new MyFragment();

        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.show_FrameLayout, homePageFragment)
                .add(R.id.show_FrameLayout, shoppingFragment)
                .add(R.id.show_FrameLayout, orderFragment)
                .add(R.id.show_FrameLayout, myFragment)
                .show(homePageFragment)
                .hide(shoppingFragment)
                .hide(orderFragment)
                .hide(myFragment)
                .commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(android.widget.RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.show_homepage :
                        transaction.show(homePageFragment)
                                .hide(shoppingFragment)
                                .hide(orderFragment)
                                .hide(myFragment);
                        break;
                    case R.id.show_shopping :
                        transaction.show(shoppingFragment)
                                .hide(homePageFragment)
                                .hide(orderFragment)
                                .hide(myFragment);
                        break;
                    case R.id.show_order :
                        transaction.show(orderFragment)
                                .hide(homePageFragment)
                                .hide(shoppingFragment)
                                .hide(myFragment);
                        break;
                    case R.id.show_my :
                        transaction.show(myFragment)
                                .hide(homePageFragment)
                                .hide(orderFragment)
                                .hide(shoppingFragment);
                        break;
                }
                transaction.commit();
            }
        });
    }

    private void XNAJ() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    //为避免底部导航栏覆盖注释掉这一行
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
