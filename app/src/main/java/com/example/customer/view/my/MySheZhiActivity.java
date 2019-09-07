package com.example.customer.view.my;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.bean.MySheZhiSexBean;
import com.example.customer.bean.UserInfoBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

public class MySheZhiActivity extends Activity implements MyContract.MyView.MySheZhiActivity {

    private TextView name;
    private SimpleDraweeView simpleDraweeView;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

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
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PMyInfozhe(MainActivity.user_id,passwordjiami);


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

