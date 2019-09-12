package com.example.customer.view.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.bean.MyYjBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyYjActivity extends AppCompatActivity implements MyContract.MyView.MyYjActivity {

    private EditText editText;
    private String passwordjiami;

    int screenWidth;//屏幕宽度
    int screenHeight;//屏幕高度
    private WindowManager windowManager;
    private PopupWindow popupWindow;

    private View inflate;
    private ImageView imageView;
    private File file;
    private File file1;
    private MultipartBody.Part data2;
    private MultipartBody.Part data3;

    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_yj);
        //返回
        back();
        //输入框
        edittext();
        //点击图片
        picture();
        //点击提交
        submission();
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        passwordjiami = PasswordJiami.passwordjiami(passwjiemi);

    }

    private void picture() {
        imageView = findViewById(R.id.my_yj_ImageView);
        inflate = LayoutInflater.from(this).inflate(R.layout.popupwindow_my_shezhi_toux, null);
        windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout relative = findViewById(R.id.my_yj_bumm);
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
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it, 100);
                popupWindow.dismiss();
            }
        });
        Popu_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_PICK);
                it.setType("image/*");
                startActivityForResult(it, 200);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == -1) {
            Bitmap bitmap = data.getParcelableExtra("data");
            imageView.setImageBitmap(bitmap);
            file1 = compressImage(bitmap);
            //String s = bitmapToBase64(bitmap);
            RequestBody body = RequestBody.create(MediaType.parse("image/jpg"), file1);
            data2 = MultipartBody.Part.createFormData("file", file1.getName(), body);

        }
        if (requestCode == 200 && resultCode == -1) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                file1 = compressImage(bitmap);
                //String s = bitmapToBase64(bitmap);
                //Log.e("图片300",s+"");
                RequestBody body = RequestBody.create(MediaType.parse("image/jpg"), file1);
                data2 = MultipartBody.Part.createFormData("file", file1.getName(), body);
            } catch (IOException e) {

            }
        }
    }
    public static File compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

        //以当前时间命名
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        //图片名
        String filename = format.format(date);
        //存储到外存空间
        File file = new File(Environment.getExternalStorageDirectory(), filename + ".jpg");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //try {
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            /*} catch (IOException e) {
                e.printStackTrace();
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private void submission() {
        Button button = findViewById(R.id.my_yj_Button);
        final String s = editText.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPresenter.ShowMyYJ(MainActivity.user_id,passwordjiami,s,data2);
                /*if (editText.getText().toString()!=null){
                    myPresenter.ShowMyYJ(MainActivity.user_id,passwordjiami,s,data2);
                }else {
                    Toast.makeText(MyYjActivity.this,"请输入反馈内容",Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }

    private void edittext() {
        editText = findViewById(R.id.my_yj_edtext);
        //点击软键盘外部，收起软键盘
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }

    private void back() {
        ImageView textView = findViewById(R.id.my_yj_back);
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
    private  void bgAlpha(float f){
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = f;
        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void ShowMyYj(Object object) {
        MyYjBean myYjBean = new MyYjBean();
        Log.e("意见反馈",myYjBean.getCode()+"---"+myYjBean.getMsg());
        if (myYjBean.getCode()==0){
            Toast.makeText(MyYjActivity.this,myYjBean.getMsg()+"",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
