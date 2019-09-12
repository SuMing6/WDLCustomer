package com.example.customer.view.homepage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.TabLayoutAdapter;
import com.example.customer.bean.HomeGoodsBean;
import com.example.customer.bean.HomeGoodsCarBean;
import com.example.customer.bean.HomePageGoodSousuoBean;
import com.example.customer.contract.MyContract;
import com.example.customer.view.homegoods.HomeDetailsFragment;
import com.example.customer.view.homegoods.HomeEvaluateFragment;
import com.example.customer.view.homegoods.HomeGoodsFragment;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homegoods.HomeGoodsSubmissionActivity;
import com.example.customer.view.homegoods.HomeSousuoActivity;
import com.example.customer.view.homegoods.HomeSousuoLsActivity;
import com.example.customer.view.homegoods.HomepageGoodssousuoActivity;
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
    //店铺id
    public static int id;
    private TextView nub;
    private TextView money;
    private HomeGoodsCarBean homeGoodsCarBeane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_homepage_goods);
        ImageView textView =findViewById(R.id.activity_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nub = findViewById(R.id.homepage_goods_nub);
        money = findViewById(R.id.homepage_goods_money);
        init();
        //获取id
        getid();
        //导航栏
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PHomePageGoodsCar(id,MainActivity.user_id,passwordjiami);
        //结算
        js();

        tabLayout();
        final EditText editText = findViewById(R.id.activity_shousuo);
        TextView sousuo = findViewById(R.id.activity_textview);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editText.getText())){

                }else {
                    Intent intent = new Intent(HomepageGoodsActivity.this, HomepageGoodssousuoActivity.class);
                    intent.putExtra("n",editText.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private void js() {
        Button button = findViewById(R.id.homepage_goods_Js);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageGoodsActivity.this, HomeGoodsSubmissionActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
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

    @Override
    public void ShowHomePageGoodsCar(Object object) {
        homeGoodsCarBeane = (HomeGoodsCarBean) object;
        Log.e("TAG","商品详情w"+ homeGoodsCarBeane.getCode()+"---"+ homeGoodsCarBeane.getData().getMoney());
        if (homeGoodsCarBeane.getMsg().equals("ok")){
            nub.setText(homeGoodsCarBeane.getData().getNumber()+"");
            money.setText("￥"+homeGoodsCarBeane.getData().getMoney());
            Log.e("TAG","商品详情n"+ homeGoodsCarBeane.getCode()+"---"+ homeGoodsCarBeane.getData().getMoney());
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
        viewPager.setAdapter(new TabLayoutAdapter(getSupportFragmentManager(), this,fragmentList,list_Title));
        tab.setupWithViewPager(viewPager);//此方法就是让tablayout和ViewPager联动
    }


    @Override
    protected void onResume() {
        super.onResume();

        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PHomePageGoodsCar(id,MainActivity.user_id,passwordjiami);
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
