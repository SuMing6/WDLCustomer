package com.example.customer.view.my;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.customer.R;

public class MyScActivity extends AppCompatActivity {

    int cont = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_sc);
        //点击编码切换全选
        ck();
        //返回
        back();
    }


    private void ck() {
        final TextView bj = findViewById(R.id.my_sc_bj);
        final TextView qx = findViewById(R.id.my_sc_qx);
        final LinearLayout linearLayout = findViewById(R.id.my_sc_bottom);
        RadioButton ok = findViewById(R.id.my_sc_ok);
        bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bj.setVisibility(View.GONE);
                qx.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                cont = 2;
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bj.setVisibility(View.VISIBLE);
                qx.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                cont = 1;
            }
        });


    }

    private void back() {
        TextView textView = findViewById(R.id.my_sc_back);
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
