package com.example.customer.view.my;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.customer.R;
import com.mob.MobSDK;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;

public class MyHyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_hy);
        //显示位置
        wizhi();
        //返回
        back();
        //点击邀请好友
        yqhy();
        String share = getIntent().getStringExtra("share");
        TextView textView = findViewById(R.id.my_yq_text);
        textView.setText(share);


    }

    private void yqhy() {
        Button button = findViewById(R.id.my_hy_yqhy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //比如分享到QQ，其他平台则只需要更换平台类名，例如Wechat.NAME则是微信
                //java
                    OnekeyShare oks = new OnekeyShare();
                    // title标题，微信、QQ和QQ空间等平台使用
                    oks.setTitle("您好测试下分享");
                    // titleUrl QQ和QQ空间跳转链接
                    //oks.setTitleUrl("http://sharesdk.cn");
                    // text是分享文本，所有平台都需要这个字段
                    oks.setText("我是分享文本");
                    // imagePath是图片的本地路径，确保SDcard下面存在此张图片

                    oks.setImagePath("/sdcard/test.jpg");
                    // url在微信、Facebook等平台中使用
                    oks.setUrl("http://sharesdk.cn");
                    // 启动分享GUI
                    oks.show(MyHyActivity.this);
            }
        });
    }


    private void wizhi() {
        RelativeLayout tou = findViewById(R.id.my_hy_sm);
        RelativeLayout body = findViewById(R.id.my_hy_nr);
        tou.bringToFront();
    }

    private void back() {
        TextView textView = findViewById(R.id.my_hy_back);
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
