package com.example.customer.view.homegoods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.adapter.HomeSousuoAdapter;
import com.example.customer.bean.HomeSouSuoBean;
import com.example.customer.contract.MyContract;
import com.example.customer.fragment.HomePageFragment;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homepage.HomepageGoodsActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeSousuoActivity extends AppCompatActivity implements MyContract.MyView.HomeSousuoActivity {

    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

    List<HomeSouSuoBean.DataBean> dataBeans = new ArrayList<>();
    private String s,t;
    private HomeSousuoAdapter sousuoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_home_sousuo);
        s=null;

        Intent intent = getIntent();
        s = intent.getStringExtra("s");
        t = intent.getStringExtra("t");
        //展示
        show();
    }

    private void show() {
        RecyclerView recyclerView = findViewById(R.id.home_sousuo_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myPresenter.PHomeSousuo(MainActivity.longitude+"",MainActivity.latitude+"", HomePageFragment.province_id,HomePageFragment.city_id,HomePageFragment.area_id,0,s,MainActivity.user_id);

        sousuoAdapter = new HomeSousuoAdapter(HomeSousuoActivity.this,dataBeans);
        recyclerView.setAdapter(sousuoAdapter);
        sousuoAdapter.setSetOnClickItem(new HomeSousuoAdapter.setOnClickItem() {
            @Override
            public void onGreat(int money) {
                Intent intent = new Intent(HomeSousuoActivity.this , HomepageGoodsActivity.class);
                intent.putExtra("id",money);
                startActivity(intent);
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

    @Override
    public void ShowHomeSousuo(Object object) {
        HomeSouSuoBean homeSouSuoBean = (HomeSouSuoBean) object;

        if (homeSouSuoBean.getCode()==0){
            dataBeans.clear();
            dataBeans.addAll(homeSouSuoBean.getData());
            sousuoAdapter.notifyDataSetChanged();
        }
    }
}
