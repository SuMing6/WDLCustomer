package com.example.customer.view.my;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.bean.MySheZhiPwdBean;
import com.example.customer.bean.ShezhiYZMBean;
import com.example.customer.bean.YAMBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

import java.util.List;

public class MySheZhiPwdActivity extends AppCompatActivity implements MyContract.MyView.MySheZhiPwdActivity{


    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private EditText oldpwd;
    private String msg;
    private String passwordjiami;
    private String passwordjiami1;
    private String passwordjiami2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_she_zhi_pwd);
        //返回
        back();
        //确认修改
        Qrxg();

    }

    private void Qrxg() {
        Button button = findViewById(R.id.my_shezhi_pwd_qr);
        oldpwd = findViewById(R.id.my_shezhi_pwd_oldpwd);
        TextView textView = findViewById(R.id.my_shezhi_pwd_YZM);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = oldpwd.getText().toString();

                String passwordjiami = PasswordJiami.passwordjiami(s);
                myPresenter.PMyShezhiYZM(passwordjiami);
            }
        });

        final EditText pwd1 = findViewById(R.id.my_shezhi_pwd_pwd1);
        final EditText pwd2 = findViewById(R.id.my_shezhi_pwd_pwd2);

        String pwdtext1 = pwd1.getText().toString();
        String pwdtext2 = pwd2.getText().toString();
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        passwordjiami = PasswordJiami.passwordjiami(passwjiemi);

        passwordjiami1 = PasswordJiami.passwordjiami(pwdtext1);
        passwordjiami2 = PasswordJiami.passwordjiami(pwdtext2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.e("修改密码",MainActivity.user_id+"---"+passwordjiami+"---"+ Integer.parseInt(msg)+"---"+passwordjiami1+"---"+passwordjiami2);
                myPresenter.PMyShezhiPwd(MainActivity.user_id, passwordjiami, Integer.parseInt(msg) , passwordjiami1, passwordjiami2);
            }
        });
    }

    private void back() {
        ImageView textView = findViewById(R.id.my_shezhi_pwd_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void ShowMySheZhiPwd(Object object) {
        MySheZhiPwdBean mySheZhiPwdBean = (MySheZhiPwdBean) object;
        if (mySheZhiPwdBean.getCode() == 0){
            if (mySheZhiPwdBean.getMsg().equals("设置成功！")){
                Toast.makeText(this,mySheZhiPwdBean.getMsg(),Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }

    @Override
    public void ShowMySheZhiYZM(Object object) {
        ShezhiYZMBean yamBean = (ShezhiYZMBean) object;
        if (yamBean.getCode()==0){
            msg = yamBean.getMsg();
            oldpwd.setText(msg);
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


}
