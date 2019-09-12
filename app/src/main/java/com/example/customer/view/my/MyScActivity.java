package com.example.customer.view.my;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.my.MyScDataAdapter;
import com.example.customer.bean.MyScAddBean;
import com.example.customer.bean.MyScDataBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class MyScActivity extends Activity implements MyContract.MyView.MyScActivity {

    //int cont = 1 ;
    List<MyScDataBean.DataBean> dataBeans = new ArrayList<>();
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private MyScDataAdapter dataAdapter;
    //收藏删除
    public static boolean sc = false ;
    List<Integer> integers = new ArrayList<>();
    List<Integer> integersP = new ArrayList<>();
    private TextView bj;
    private TextView qx;
    private RadioButton ok;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_my_sc);
        //点击编码切换全选
        ck();
        //返回
        back();
        //展示
        data();
        if (dataBeans.size()==0){
            dataAdapter.notifyDataSetChanged();
        }
    }

    private void data() {
        RecyclerView recyclerView = findViewById(R.id.my_sc_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String s = MainActivity.token;
        String passwjiemi = PasswordJiami.passwjiemi(s);
        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);

        myPresenter.PMyScData(MainActivity.user_id,passwordjiami,MainActivity.longitude+"",MainActivity.latitude+"");

        dataAdapter = new MyScDataAdapter(MyScActivity.this,dataBeans);
        recyclerView.setAdapter(dataAdapter);

        dataAdapter.setSetOnClickItem(new MyScDataAdapter.setOnClickItem() {
            @Override
            public void onGreat(int mid, int position) {
                integers.add(mid);
                integersP.add(position);
            }
        });
    }

    @Override
    public void ShowMyScData(Object object) {
        MyScDataBean myScDataBean = (MyScDataBean) object;
        dataBeans.clear();
        if (myScDataBean.getCode()==0){
            //Log.e("收藏的",myScDataBean.getData().get(1).getShop().getShop_logo());
            dataBeans.addAll(myScDataBean.getData());
            dataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void PMyScDataDel(Object object) {
        MyScAddBean myScAddBean = (MyScAddBean) object;
        if (myScAddBean.getCode()==0){
            Toast.makeText(this,myScAddBean.getMsg(),Toast.LENGTH_SHORT).show();
            onCreate(null);
            integers.clear();
            sc=false ;
            String s = MainActivity.token;
            String passwjiemi = PasswordJiami.passwjiemi(s);
            String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
            myPresenter.PMyScData(MainActivity.user_id,passwordjiami,MainActivity.longitude+"",MainActivity.latitude+"");
            //dataAdapter.notifyDataSetChanged();
        }
        Toast.makeText(this,myScAddBean.getMsg(),Toast.LENGTH_SHORT).show();
    }


    private void ck() {
        bj = findViewById(R.id.my_sc_bj);
        qx = findViewById(R.id.my_sc_qx);
        linearLayout = findViewById(R.id.my_sc_bottom);
        ok = findViewById(R.id.my_sc_ok);
        RadioButton del = findViewById(R.id.my_sc_del);
        bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bj.setVisibility(View.GONE);
                //qx.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                //cont = 2;
                sc=true ;
                dataAdapter.notifyDataSetChanged();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bj.setVisibility(View.VISIBLE);
                qx.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                //cont = 1;
                sc=false ;
                integersP.clear();
                integers.clear();
                dataAdapter.notifyDataSetChanged();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < integersP.size() ; i++) {
                    if (dataBeans.size()==0){
                        dataBeans = null ;
                        dataAdapter.notifyDataSetChanged();
                    }else {
                        dataAdapter.notifyItemRemoved(integersP.get(i));
                        Log.e("收藏的",integersP.get(i)+"");

                    }
                }
                for (int i = 0; i < integers.size() ; i++) {
                    String s = MainActivity.token;
                    String passwjiemi = PasswordJiami.passwjiemi(s);
                    String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
                    myPresenter.PMyScDataDel(MainActivity.user_id,passwordjiami,integers.get(i));
                }
                dataAdapter.notifyDataSetChanged();
            }
        });





    }

    private void back() {
        ImageView textView = findViewById(R.id.my_sc_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        bj.setVisibility(View.VISIBLE);
        qx.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        //cont = 1;
        sc=false ;
        integersP.clear();
        integers.clear();
        dataAdapter.notifyDataSetChanged();
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
