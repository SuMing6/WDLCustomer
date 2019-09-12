package com.example.customer.activity;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.customer.R;
import com.example.customer.bean.LoginBean;
import com.example.customer.bean.YAMBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.util.EndApp;
import com.example.customer.util.RsaUtils;
import com.example.customer.voice.LanContextWrapper;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class MainActivity extends Activity implements MyContract.MyView.MainActivity {

    //经
    public static double longitude;
    //纬
    public static double latitude;
    //国家，省，市，区，街道
    public static String country;
    public static String province;
    public static String city;
    public static String district;
    public static String street;

    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    /* 密钥内容 base64 code */
    private static String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDrouoy4rro4ICiwC+re4/uMZIylYtDXb1KnCBpKMgKLgB0GvI+L3rhONcWz40N0ar3wLLzffAgwNUJvc9m5EM/pgf0PnckzTK+bluA7enNb3dbXpqBV0Yu69ufv8hqhwpI3HB2csIvUqzPXtf7WHrMB8IGQCk67Y03ZCq4Kra5wIDAQAB";
/**
        * 初始化定位参数配置
 */

    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getApplicationContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();
        int selfPermission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (selfPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.BLUETOOTH,
                            Manifest.permission.CAMERA},
                    100);
        }else {
        }

    }

    /**
     * 实现定位回调
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            //获取纬度信息
            latitude = location.getLatitude();
            //获取经度信息
            longitude = location.getLongitude();
            country = location.getCountry();    //获取国家

            province = location.getProvince();    //获取省份
            city = location.getCity();    //获取城市
            district = location.getDistrict();    //获取区县.

            street = location.getStreet();    //获取街道信息

            //获取定位精度，默认值为0.0f
            float radius = location.getRadius();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            String coorType = location.getCoorType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            int errorCode = location.getLocType();
            Log.e("AGE","获取经度："+longitude+"获取伟度："+latitude+"省："+province+"市："+city+"区："+district+"街道："+street);
            Log.e("AGE","获取经度："+location.getLocType());
        }
    }


   /* //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    //获取纬度
                    latitude = aMapLocation.getLatitude();
                    //获取经度
                    longitude = aMapLocation.getLongitude();
                    aMapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //textView.setText(aMapLocation.getCity());
                    Date date = new Date(aMapLocation.getTime());
                    df.format(date);//定位时间
                    aMapLocation.getCity();
                    province = aMapLocation.getProvince();//省信息
                    city = aMapLocation.getCity();//城市信息
                    //定位城区
                    district = aMapLocation.getDistrict();
                    //国家信息
                    country = aMapLocation.getCountry();
                    //街道信息
                    street = aMapLocation.getStreet();
                    Log.e("AGE","获取经度："+aMapLocation.getLongitude()+"获取伟度："+aMapLocation.getLatitude()+"地名："+aMapLocation.getCity().toString());
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
    };*/




    private CheckBox language_zg;
    private CheckBox language_jpz;
    private RadioGroup loging_radioGroup;
    private SharedPreferences sp;
    private String source;
    private EditText edit_pwd;
    private EditText edit_phone;

    public static int user_id;
    public static String token;
    public static String yzm;


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
        //gaode();
        language();
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                //异常处理
            }
        });


    }

    private void Loging() {
        Button button = findViewById(R.id.loging_login);
        edit_phone = findViewById(R.id.main_phone);
        edit_pwd = findViewById(R.id.main_pwd);
        TextView YZM = findViewById(R.id.main_Yzm);
        YZM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = edit_phone.getText().toString();
                String pwd = edit_pwd.getText().toString();
                //SRA加密
                String passwordjiami = PasswordJiami.passwordjiami(phone);
                myPresenter.PMainactivityYzm(passwordjiami);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = edit_phone.getText().toString();
                String pwd = edit_pwd.getText().toString();
                String passwordjiami = PasswordJiami.passwordjiami(phone);
                if (pwd!=null && passwordjiami!=null){
                    myPresenter.PMainactivityLogin(passwordjiami, Integer.parseInt(pwd));
                }else {
                    Toast.makeText(MainActivity.this,"账号密码不可为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    @Override
    public void MainactivityYzm(Object object) {
        YAMBean yamBean = (YAMBean) object;
        if (yamBean.getCode()==0){
            yzm = yamBean.getMsg();
            edit_pwd.setText(yamBean.getMsg());
        }else {
            Toast.makeText(MainActivity.this, yamBean.getMsg() + "", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void MainactivityLogin(Object object) {
        LoginBean loginBean = (LoginBean) object;
        Toast.makeText(MainActivity.this,loginBean.getMsg()+"",Toast.LENGTH_LONG).show();
        token = loginBean.getToken();
        user_id = loginBean.getUser_id();
        if (loginBean.getCode()==0){
            initLocationOption();
            Intent intent = new Intent(this,ShowActivity.class);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(MainActivity.this,loginBean.getMsg()+"",Toast.LENGTH_LONG).show();
        }
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------*/
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

    /*private void gaode() {
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
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100){
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                //mLocationClient.startLocation();
                initLocationOption();
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
        ActionBar actionBar = getActionBar();
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

    @Override
    protected void onResume() {
        super.onResume();
        initLocationOption();
    }

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
