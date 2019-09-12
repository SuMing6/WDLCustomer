package com.example.customer.view.homegoods;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.SubmissionHBAdapter;
import com.example.customer.bean.SubmissionHBBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homepage.HomepageGoodsActivity;

import java.util.ArrayList;
import java.util.List;

public class SubmissionHBActivity extends AppCompatActivity implements MyContract.MyView.SubmissionHBActivity {

    List<SubmissionHBBean.DataBean> dataBeans = new ArrayList<>();
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private SubmissionHBAdapter adapter;
    public static String mo;
    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_submission_hb);
        //数据
        data();
        //返回
        back();
    }

    private void data() {
        RecyclerView recyclerView = findViewById(R.id.submission_hb_RecyclerView);
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PHB(HomepageGoodsActivity.id, MainActivity.user_id,passwordjiami);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SubmissionHBAdapter(this,dataBeans);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setSetOnClickItem(new SubmissionHBAdapter.setOnClickItem() {
            @Override
            public void onGreat(int eid, int position, String money) {
                id = eid ;
                mo = money;
                finish();
            }
        });


    }

    private void back() {
        ImageView textView = findViewById(R.id.submission_hb_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void ShowSubmissionHB(Object object) {
        SubmissionHBBean submissionHBBean = (SubmissionHBBean) object;
        if (submissionHBBean.getCode()==0){
            dataBeans.addAll(submissionHBBean.getData());
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this,"抱歉您没有红包", Toast.LENGTH_SHORT).show();
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
