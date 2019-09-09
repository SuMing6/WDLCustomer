package com.example.customer.view.my;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.bean.MySheZhiSexBean;
import com.example.customer.bean.MySheZhiTXBean;
import com.example.customer.bean.UserInfoBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MySheZhiActivity extends Activity implements MyContract.MyView.MySheZhiActivity {


    int screenWidth;//屏幕宽度
    int screenHeight;//屏幕高度
    private WindowManager windowManager;
    private PopupWindow popupWindow;

    private View inflate;

    private TextView name;
    private SimpleDraweeView simpleDraweeView;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

    private File file;
    private String passwordjiami;

    /*------------------------------------*/

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    //private ImageView iv_personal_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_she_zhi);
        name = findViewById(R.id.my_shezhi_name);
        simpleDraweeView = findViewById(R.id.my_shezhi_headimg);
        radioButton1 = findViewById(R.id.my_shezhi_button1);
        radioButton2 = findViewById(R.id.my_shezhi_button2);

        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PMyInfozhe(MainActivity.user_id, passwordjiami);




        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySheZhiActivity.this, MySheZhiNameActivity.class);
                startActivity(intent);
            }
        });
        //设置支付密码
        setPwd();
        //退出登录
        outLogin();
        //返回
        back();
        //设置性别
        setSex();
        //头像
        touXiang();



    }

    private void touXiang() {
        SimpleDraweeView my_shezhi_headimg = findViewById(R.id.my_shezhi_headimg);

        inflate = LayoutInflater.from(this).inflate(R.layout.popupwindow_my_shezhi_toux, null);
        windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
        my_shezhi_headimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout relative = findViewById(R.id.my_shezhi_bumm);
                popupWindow = new PopupWindow(inflate, screenWidth, 550, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setTouchable(true);
                popupWindow.showAsDropDown(relative, 0, relative.getHeight());
                //变色
                bgAlpha(0.618f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        bgAlpha(1.0f);
                    }
                });
            }
        });
        TextView Popu_textView = inflate.findViewById(R.id.popupwindow_my_shezhi_toux_paizhao);
        TextView Popu_pic = inflate.findViewById(R.id.popupwindow_my_shezhi_toux_pic);
        TextView Popu_quxiao = inflate.findViewById(R.id.popupwindow_my_shezhi_toux_quxiao);
        Popu_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quxian();
            }
        });
        Popu_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAlbumIntent = new Intent(
                        Intent.ACTION_GET_CONTENT);
                openAlbumIntent.setType("image/*");
                startActivityForResult(openAlbumIntent, 200);
                popupWindow.dismiss();
            }
        });
        Popu_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    private void quxian(){
        if(Build.VERSION.SDK_INT>=23){
            ActivityCompat.requestPermissions(MySheZhiActivity.this,new String[]{Manifest.permission.CAMERA},0);
            int permission = ContextCompat.checkSelfPermission(MySheZhiActivity.this.getApplicationContext(), Manifest.permission.CAMERA);
            if(permission== PackageManager.PERMISSION_GRANTED){
                //如果有了相机的权限就调用相机
                //打开相机
                Intent openCameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCameraIntent, 100);
                popupWindow.dismiss();
            }else {
                AlertDialog.Builder builder=new AlertDialog.Builder(MySheZhiActivity.this);
                builder.setTitle("提示");
                builder.setMessage("是否开启相机权限");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //去请求相机权限
                        ActivityCompat.requestPermissions(MySheZhiActivity.this,new String[]{Manifest.permission.CAMERA},0);
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MySheZhiActivity.this, "您拒绝了开启相机权限", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        }else {
            //不是6.0直接调用相机
            //打开相机
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode == -1){

            Bitmap  parcelableExtra = data.getParcelableExtra("data");
            File file = compressImage(parcelableExtra);
            Log.e("图片100",file+"");
            simpleDraweeView.setImageBitmap(parcelableExtra);
            //myPresenter.ShowMySheZhiTouxiang(MainActivity.user_id,passwordjiami,file);
        }else if (requestCode==200 && resultCode == -1){
            Uri uri = data.getData();
            simpleDraweeView.setImageURI(uri);
            Log.e("图片200",uri+"");
        }
    }

    public static File compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        //以当前时间命名
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        //图片名
        String filename = format.format(date);
        //存储到外存空间
        File file = new File(Environment.getExternalStorageDirectory(), filename + ".jpg");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }




    private  void bgAlpha(float f){
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = f;
        getWindow().setAttributes(layoutParams);
    }

    private void setSex() {
        /*RadioGroup radioGroup = findViewById(R.id.my_shezhi_RadioGroup);
        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                radioButton1.setChecked(b);
            }
        });
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                radioButton2.setChecked(b);
            }
        });*/
    }


    private void setPwd() {
        RelativeLayout relativeLayout = findViewById(R.id.my_shezhi_setPwd);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySheZhiActivity.this, MySheZhiPwdActivity.class);
                startActivity(intent);
            }
        });

    }

    private void outLogin() {
        Button button = findViewById(R.id.my_shezhi_outlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySheZhiActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void back() {
        TextView textView = findViewById(R.id.my_shezhi_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void ShowMySheZhiif(Object object) {
        UserInfoBean userInfoBean = (UserInfoBean) object;
        if (userInfoBean.getCode()==0){
            if (userInfoBean.getData().getNickname()!=null){
                name.setText(userInfoBean.getData().getNickname());
            }
            if (userInfoBean.getData().getHeadimg()!=null){
                simpleDraweeView.setImageURI(userInfoBean.getData().getHeadimg());
            }
            if (userInfoBean.getData().getSex() == 1) {
                radioButton1.setChecked(true);
            }
            if (userInfoBean.getData().getSex() == 2) {
                radioButton2.setChecked(true);
            }
        }
    }

    @Override
    public void ShowMySheZhiSex(Object object) {
        MySheZhiSexBean mySheZhiSexBean = (MySheZhiSexBean) object;
        if (mySheZhiSexBean.getCode()==0){
            Toast.makeText(this,""+mySheZhiSexBean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void ShowMySheZhiTouxiang(Object object) {
        MySheZhiTXBean mySheZhiTXBean = (MySheZhiTXBean) object;
        if (mySheZhiTXBean.getCode()==0){
            Toast.makeText(this,""+mySheZhiTXBean.getMsg(),Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        if (radioButton1.isChecked()==true) {
            myPresenter.PMyInfozhesex(MainActivity.user_id, passwordjiami, 1);
        }
        if (radioButton2.isChecked()==true){
            myPresenter.PMyInfozhesex(MainActivity.user_id, passwordjiami, 2);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onCreate(null);
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

