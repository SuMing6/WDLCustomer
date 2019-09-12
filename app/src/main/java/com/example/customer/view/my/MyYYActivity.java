package com.example.customer.view.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.voice.LanContextWrapper;

public class MyYYActivity extends AppCompatActivity {


    //  修改语言
    public static final String SP_NAME = "name";
    public static final String LANGUAGE = "language";
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_yy);
        //点击切换
        click();
        ImageView imageView = findViewById(R.id.my_yy_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void click() {
        TextView cn = findViewById(R.id.my_yy_cn);
        TextView jpz = findViewById(R.id.my_yy_jpz);
        sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("China", true).commit();
                SharedPreferences sharedPreferences=getSharedPreferences(MainActivity.SP_NAME, MODE_PRIVATE);
                sharedPreferences.edit().putString(MainActivity.LANGUAGE, LanContextWrapper.LANG_CN).apply();
                rebot();
            }
        });
        jpz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("China", false).commit();
                SharedPreferences sharedPreferences=getSharedPreferences(MainActivity.SP_NAME, MODE_PRIVATE);
                sharedPreferences.edit().putString(MainActivity.LANGUAGE, LanContextWrapper.LANG_EN).apply();
                rebot();
            }
        });
    }
    private void rebot() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
            Intent intent = new Intent(this, MyYYActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            /*overridePendingTransition(R.anim.anim_change_lang_enter, R.anim.anim_change_lang_exit);*/
            finish();
        }else{
            recreate();
        }
    }
    /**
     * 设置修改语言
     *
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LanContextWrapper.wrap(newBase);
        super.attachBaseContext(context);
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
