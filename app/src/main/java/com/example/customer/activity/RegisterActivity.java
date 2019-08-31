package com.example.customer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.bean.RegisterBean;
import com.example.customer.bean.YAMBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.util.RsaUtils;

import java.security.PublicKey;

public class RegisterActivity extends AppCompatActivity implements MyContract.MyView.RegisterActivity {

    private EditText editPwd;
    private EditText editPhone;
    private String phone;
    private String pwd;
    private TextView yzm;

    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_register);
        //初始化
        init();
        //验证码
        YZM();
        //注册
        Register();
        //返回登录
        BackLogin();

    }


    private void init() {
        editPhone = findViewById(R.id.reg_phone);
        editPwd = findViewById(R.id.reg_pwd);
        yzm = findViewById(R.id.reg_Yzm);

        phone = editPhone.getText().toString();
        pwd = editPwd.getText().toString();
    }
    private void YZM() {
        yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passwordjiami = PasswordJiami.passwordjiami(phone);
                myPresenter.PRegisteractivityYzm(passwordjiami);
            }
        });
    }

    private void Register() {
        Button button = findViewById(R.id.reg_reg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passwordjiami = PasswordJiami.passwordjiami(phone);
                pwd = editPwd.getText().toString();
                myPresenter.PRegister(passwordjiami, Integer.parseInt(pwd));
            }
        });
        /*Intent intent = new Intent(MainActivity.this, ShowActivity.class);
        startActivity(intent);
        finish();*/
    }

    @Override
    public void RegisterYzm(Object object) {
        YAMBean yamBean = (YAMBean) object;
        if (yamBean.getCode() == 0){
            editPwd.setText(yamBean.getMsg());
        }
    }

    @Override
    public void ShowRegisterR(Object object) {
        RegisterBean registerBean = (RegisterBean) object;
        if (registerBean.getCode() == 0){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(RegisterActivity.this,registerBean.getMsg()+"",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(RegisterActivity.this,registerBean.getMsg()+"",Toast.LENGTH_LONG).show();
        }
    }

    /*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private void BackLogin() {
        TextView textView = findViewById(R.id.reg_login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
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

