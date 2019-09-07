package com.example.customer.view.homegoods;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.SubmissionDZAdapter;
import com.example.customer.bean.DzBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class SubmissionDZActivity extends AppCompatActivity implements MyContract.MyView.SubmissionDZActivity {


    List<DzBean.DataBean> dataBeans = new ArrayList<>();
    private SubmissionDZAdapter dzAdapter;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    public static int id ;
    public static String address ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_submission_dz);
        //返回
        back();
        //  定位位置
        dz();
        //数据
        sj();

    }

    private void sj() {
        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
        myPresenter.PSubmissionDZ(MainActivity.user_id,passwordjiami);
        RecyclerView recyclerView = findViewById(R.id.submission_dz_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dzAdapter = new SubmissionDZAdapter(this,dataBeans);
        recyclerView.setAdapter(dzAdapter);
        dzAdapter.setSetOnClickItem(new SubmissionDZAdapter.setOnClickItem() {
            @Override
            public void onGreat(int eid, int position, String add) {
                id = eid;
                address = add;
                finish();
            }
        });
    }

    private void dz() {
        TextView dzname = findViewById(R.id.activity_submission_dz_name);
        dzname.setText(MainActivity.country+MainActivity.province+MainActivity.city+MainActivity.district+MainActivity.street);
    }

    @Override
    public void ShowSubmissionDZ(Object object) {
        DzBean dzBean = (DzBean) object;
        Log.e("地址咋",dzBean.getCode()+"--"+dzBean.getData());
        if (dzBean.getCode()==0){
            dataBeans.addAll(dzBean.getData());
            dzAdapter.notifyDataSetChanged();
        }
    }

    private void back() {
        TextView textView = findViewById(R.id.submission_dz_back);
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


}
