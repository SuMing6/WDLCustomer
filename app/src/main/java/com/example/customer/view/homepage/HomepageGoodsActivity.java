package com.example.customer.view.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.adapter.TabLayoutAdapter;
import com.example.customer.bean.HomeGoodsBean;
import com.example.customer.contract.MyContract;
import com.example.customer.fragment.homegoods.HomeDetailsFragment;
import com.example.customer.fragment.homegoods.HomeEvaluateFragment;
import com.example.customer.fragment.homegoods.HomeGoodsFragment;
import com.example.customer.fragment.myticket.MyTicketHBFragment;
import com.example.customer.fragment.myticket.MyTicketKQFragment;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.my.MyTicketActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class HomepageGoodsActivity extends AppCompatActivity implements MyContract.MyView.HomepageGoodsActivity{

    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private TextView shong;
    private TextView shou;
    private TextView jian;
    private TextView score;
    private TextView name;
    private SimpleDraweeView simpleDraweeView;
    private HomeGoodsBean homeGoodsBean;
    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_homepage_goods);
        init();
        //获取id
        getid();
        //导航栏
        tabLayout();

    }



    private void init() {
        shong = findViewById(R.id.homepage_goods_shong);
        shou = findViewById(R.id.homepage_goods_shou);
        jian = findViewById(R.id.homepage_goods_jian);
        score = findViewById(R.id.homepage_goods_score);
        name = findViewById(R.id.homepage_goods_name);
        simpleDraweeView = findViewById(R.id.homepage_goods_SimpleDraweeView);

    }

    private void getid() {
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        myPresenter.PHomepageGoods(id);
    }

    @Override
    public void ShowHomePageGoods(Object object) {
        homeGoodsBean = (HomeGoodsBean) object;
        if (homeGoodsBean.getCode() == 0){
            //Log.e("TAG","商品详情"+ homeGoodsBean.getData());
            simpleDraweeView.setImageURI(homeGoodsBean.getData().getInfo().getShop_logo());
            simpleDraweeView.bringToFront();
            name.setText(homeGoodsBean.getData().getInfo().getShop_name());
            score.setText(homeGoodsBean.getData().getInfo().getScore());
            /*for (int i = 0; i < homeGoodsBean.getData().getCate().size(); i++) {*//*
                jian.setText(homeGoodsBean.getData().getCoupon().get(i).get);
                shou.setText(homeGoodsBean.getData().getInfo().getShop_name());
                shong.setText(homeGoodsBean.getData().getInfo().getShop_name());*//*
            }*/
        }else {

        }
    }
    private void tabLayout() {
        TabLayout tab = findViewById(R.id.homepage_goods_TabLayout);
        ViewPager viewPager = findViewById(R.id.homepage_goods_ViewPager);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        ArrayList<String> list_Title = new ArrayList<>();

        fragmentList.add(new HomeGoodsFragment());
        fragmentList.add(new HomeEvaluateFragment());
        fragmentList.add(new HomeDetailsFragment());

        list_Title.add("商品");
        list_Title.add("评价");
        list_Title.add("详情");

        viewPager.setAdapter(new TabLayoutAdapter(getSupportFragmentManager(), this,fragmentList,list_Title));
        tab.setupWithViewPager(viewPager);//此方法就是让tablayout和ViewPager联动
    }
/*-------------------------------------------------------------------------------------------------*/
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
