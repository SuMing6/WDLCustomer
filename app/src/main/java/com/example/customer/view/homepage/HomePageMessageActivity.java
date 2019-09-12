package com.example.customer.view.homepage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.bean.TongzhiBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

public class HomePageMessageActivity extends AppCompatActivity implements MyContract.MyView.HomePageMessageActivity{

    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_home_page_message);
        //点击返回
        back();
        //点击条目
        ock();
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PTongZhi(MainActivity.user_id,passwordjiami);
    }

    private void ock() {
        LinearLayout linearLayout1 = findViewById(R.id.homepage_message_LinearLayout1);
        LinearLayout linearLayout2 = findViewById(R.id.homepage_message_LinearLayout2);
        LinearLayout linearLayout3 = findViewById(R.id.homepage_message_LinearLayout3);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageMessageActivity.this,HomePageDingDan.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageMessageActivity.this,HomePageDingDan.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageMessageActivity.this,HomePageMessageCustomer.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void ShowTongZhi(Object object) {
        TongzhiBean tongzhiBean = (TongzhiBean) object;
        TextView home_page_message_dingdan_msg = findViewById(R.id.home_page_message_dingdan_msg);
        TextView home_page_message_dingdan_time = findViewById(R.id.home_page_message_dingdan_time);
        TextView home_page_message_xitong_msg = findViewById(R.id.home_page_message_xitong_msg);
        TextView home_page_message_xitong_time = findViewById(R.id.home_page_message_xitong_time);
        if (tongzhiBean.getCode()==0){
            home_page_message_dingdan_msg.setText(tongzhiBean.getData().getOrder().getMsg());
            home_page_message_dingdan_time.setText(tongzhiBean.getData().getOrder().getAdd_time());
        }
    }
    private void back() {
        ImageView textView = findViewById(R.id.homepage_message_back);
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
