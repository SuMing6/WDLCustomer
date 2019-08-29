package com.example.customer.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.customer.R;
import com.example.customer.fragment.HomePageFragment;
import com.example.customer.voice.LanContextWrapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    public static String district;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    aMapLocation.getLatitude();//获取纬度
                    aMapLocation.getLongitude();//获取经度
                    aMapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //textView.setText(aMapLocation.getCity());
                    Date date = new Date(aMapLocation.getTime());
                    df.format(date);//定位时间
                    aMapLocation.getCity();
                    //定位城区
                    district = aMapLocation.getDistrict();
                    Log.e("AGE","获取经度："+aMapLocation.getLongitude()+"获取伟度："+aMapLocation.getLatitude()+"地名："+aMapLocation.getCity().toString()+"城区："+aMapLocation.getDistrict()+"街道："+aMapLocation.getStreet());
                    //Log.e("AGE","获取经度："+aMapLocation.getLongitude()+"获取伟度："+aMapLocation.getLatitude()+"地名："+aMapLocation.getCity().toString());
                    //位置
                    //输入框失去焦点
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                    Log.e("AGE","获取经度："+aMapLocation.getLongitude()+"获取伟度："+aMapLocation.getLatitude());
                }
            }
        }
    };




    private CheckBox language_zg;
    private CheckBox language_jpz;
    private RadioGroup loging_radioGroup;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_main);
        //登录
        Loging();
        //注册
        Register();
        //地址
        gaode();
        language();

    }

    private void Loging() {
        Button button = findViewById(R.id.loging_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /*Intent intent = new Intent(MainActivity.this, ShowActivity.class);
        startActivity(intent);
        finish();*/
    }

    private void Register() {
        TextView textView = findViewById(R.id.main_register);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void language() {
        language_zg = findViewById(R.id.language_zg);
        language_jpz = findViewById(R.id.language_jpz);
        loging_radioGroup = findViewById(R.id.loging_RadioGroup);

        sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        boolean China = sp.getBoolean("China", true);
        if (China==true){
            language_zg.setChecked(true);
            language_jpz.setChecked(false);
        }else {
            language_zg.setChecked(false);
            language_jpz.setChecked(true);
        }
        language_zg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("China", true).commit();
                SharedPreferences sharedPreferences=getSharedPreferences(MainActivity.SP_NAME, MODE_PRIVATE);
                sharedPreferences.edit().putString(MainActivity.LANGUAGE, LanContextWrapper.LANG_CN).apply();
                rebot();
            }
        });
        language_jpz.setOnClickListener(new View.OnClickListener() {
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
    /**
     * 更改应用语言
     *
     * @param
     */
    /*public void changeAppLanguage(Locale locale) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        getResources().updateConfiguration(configuration, metrics);
        //重新启动Activity
        *//*Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*//*
        Configuration config = getResources().getConfiguration();
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }*/


    private void gaode() {
        //初始化定位
        mLocationClient = new AMapLocationClient(MainActivity.this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        AMapLocationClientOption option = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        option.setInterval(60000);
        mLocationClient.setLocationOption(option);

        int selfPermission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (selfPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.BLUETOOTH},
                    100);
        }else {
            mLocationClient.startLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100){
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                mLocationClient.startLocation();
            }
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


    //  修改语言
    public static final String SP_NAME = "name";
    public static final String LANGUAGE = "language";
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

    /*public void changeRChinese(View view) {
        SharedPreferences sharedPreferences=getSharedPreferences(MainActivity.SP_NAME, MODE_PRIVATE);
        sharedPreferences.edit().putString(MainActivity.LANGUAGE, LanContextWrapper.LANG_HK).apply();
        rebot();
    }*/

    private void rebot() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            /*overridePendingTransition(R.anim.anim_change_lang_enter, R.anim.anim_change_lang_exit);*/
            finish();
        }else{
            recreate();
        }
    }

}
