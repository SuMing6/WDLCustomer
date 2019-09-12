package com.example.customer.view.homepage;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.HomeDianDanAdapter;
import com.example.customer.bean.DianDanBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class HomePageDingDan extends AppCompatActivity implements MyContract.MyView.HomePageDingDan{

    List<DianDanBean.DataBean> dataBeans = new ArrayList<>();
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private HomeDianDanAdapter dianDanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_home_page_ding_dan);
        ImageView textView = findViewById(R.id.homepage_message_diandan_back);
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
        if (id==2){
            TextView textViewt = findViewById(R.id.homepage_message_diandan_TextView);
            textViewt.setText("系统消息");
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.homepage_message_diandan_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PDianDan(MainActivity.user_id,passwordjiami,id);

        dianDanAdapter = new HomeDianDanAdapter(this,dataBeans);
        recyclerView.setAdapter(dianDanAdapter);

    }

    @Override
    public void ShowDianDan(Object object) {
        DianDanBean dianDanBean = (DianDanBean) object;
        Log.e("订单",dianDanBean.getCode()+"");
        if (dianDanBean.getCode()==0){
            dataBeans.addAll(dianDanBean.getData());
            dianDanAdapter.notifyDataSetChanged();
        }
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
