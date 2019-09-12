package com.example.customer.view.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.bean.MySheZhiNameBean;
import com.example.customer.contract.MyContract;
import com.example.customer.fragment.MyFragment;
import com.example.customer.presenter.MyPresenter;

public class MySheZhiNameActivity extends AppCompatActivity implements MyContract.MyView.MySheZhiNameActivity {

    private ImageView back;
    private TextView ok;
    private EditText name;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_she_zhi_name);
        back = findViewById(R.id.my_shezhi_name_back);
        ok = findViewById(R.id.my_shezhi_name_ok);
        name = findViewById(R.id.my_shezhi_name_name);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = name.getText().toString();
                String token = MainActivity.token;
                String passwjiemi = PasswordJiami.passwjiemi(token);
                String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
                myPresenter.PMyShezhiName(MainActivity.user_id,passwordjiami,s);
            }
        });

    }

    @Override
    public void ShowMyShezhiName(Object object) {
        MySheZhiNameBean mySheZhiNameBean = (MySheZhiNameBean) object;
        if (mySheZhiNameBean.getCode() == 0){
            if (mySheZhiNameBean.getMsg().equals("昵称设置成功！")){
                //Toast.makeText(this,""+mySheZhiNameBean.getMsg(),Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(MySheZhiNameActivity.this, MyFragment.class);
                startActivity(intent);*/
                finish();
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
}
