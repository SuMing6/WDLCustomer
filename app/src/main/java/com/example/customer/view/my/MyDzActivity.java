package com.example.customer.view.my;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.my.MyDzAdapter;
import com.example.customer.bean.MyDzBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class MyDzActivity extends Activity implements MyContract.MyView.MyDzActivity{

    private MyDzAdapter adapter;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    List<MyDzBean.DataBean> dataBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_dz);
        //返回
        back();
        //新增收货地址
        add();
        //地址展示
        dzShow();

    }

    private void dzShow() {
        RecyclerView recyclerView = findViewById(R.id.my_dz_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String token = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(token);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PMyDz(MainActivity.user_id,passwordjiami);

        adapter = new MyDzAdapter(this,dataBeans);
        recyclerView.setAdapter(adapter);


    }

    private void add() {
        Button button = findViewById(R.id.my_dz_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyDzActivity.this,MyDzAddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void back() {
        TextView textView = findViewById(R.id.my_dz_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void ShowMyDz(Object object) {
        MyDzBean myDzBean = (MyDzBean) object;
        if (myDzBean.getCode() ==0){
            dataBeans.clear();
            dataBeans.addAll(myDzBean.getData());
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        String token = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(token);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PMyDz(MainActivity.user_id,passwordjiami);
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
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


}
