package com.example.customer.view.my;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.adapter.TabLayoutAdapter;
import com.example.customer.fragment.myticket.MyTicketHBFragment;
import com.example.customer.fragment.myticket.MyTicketKQFragment;

import java.util.ArrayList;

public class MyTicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_ticket);
        //返回
        back();
        //头部导航栏
        head();

    }

    private void head() {
        TabLayout tabLayout = findViewById(R.id.my_ticket_TabLayout);
        ViewPager viewPager = findViewById(R.id.my_ticket_ViewPage);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        ArrayList<String> list_Title = new ArrayList<>();

        fragmentList.add(new MyTicketHBFragment());
        fragmentList.add(new MyTicketKQFragment());

        list_Title.add("红包");
        list_Title.add("卡券");

        viewPager.setAdapter(new TabLayoutAdapter(getSupportFragmentManager(),MyTicketActivity.this,fragmentList,list_Title));
        tabLayout.setupWithViewPager(viewPager);//此方法就是让tablayout和ViewPager联动
    }

    private void back() {
        TextView textView = findViewById(R.id.my_ticket_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
