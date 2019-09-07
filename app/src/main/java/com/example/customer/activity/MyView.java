package com.example.customer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.bean.OrderMyViewBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

public class MyView extends AppCompatActivity implements MyContract.MyView.OrderMyViewz {
    private RatingBar mRatingBar;

    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_myview_star);
        mRatingBar = (RatingBar)findViewById(R.id.mRatingBar);

        //退出
        back();
        //提交
        commin();

        /*mRatingBar.setRating(Float.parseFloat("2.0"));
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                Toast.makeText(MyView.this, "rating:" + String.valueOf(rating),
                        Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void commin() {
        Button button = findViewById(R.id.myview_star_button);
        final EditText editText = findViewById(R.id.myview_star_edittext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                int id = intent.getExtras().getInt("id");
                String www = editText.getText().toString();
                float rating = mRatingBar.getRating();
                int pj = (int) rating;
                String s = MainActivity.token;
                String passwjiemi = PasswordJiami.passwjiemi(s);
                String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
                myPresenter.POrderMyView(MainActivity.user_id,passwordjiami,id,pj,www);
            }
        });
    }

    private void back() {
        TextView textView = findViewById(R.id.myview_star_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void ShowOrderMyView(Object object) {
        OrderMyViewBean orderMyViewBean = (OrderMyViewBean) object;
        if (orderMyViewBean.getCode()==0){
            Toast.makeText(MyView.this,""+orderMyViewBean.getMsg(),Toast.LENGTH_SHORT).show();
            finish();
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
