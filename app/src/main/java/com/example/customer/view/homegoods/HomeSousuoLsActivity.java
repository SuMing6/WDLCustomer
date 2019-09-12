package com.example.customer.view.homegoods;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.adapter.HomeSouSuoLsAdapter;
import com.example.customer.bean.HomeSousuoLsBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeSousuoLsActivity extends Activity implements MyContract.MyView.HomeSousuoLsActivity {

    List<HomeSousuoLsBean.ListBean> listBeans = new ArrayList<>();
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private HomeSouSuoLsAdapter souSuoAdapter;
    private String s;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_home_sousuo_ls);
        //搜索
        sousuo();

    }

    private void sousuo() {
        editText = findViewById(R.id.home_sousuo_EditText);
        TextView textView = findViewById(R.id.home_sousuo_text);
        final RecyclerView recyclerView = findViewById(R.id.home_sousuo_RecyclerView);
        //Log.e("搜索",HomePageFragment.province_id+"--"+HomePageFragment.city_id+"--"+HomePageFragment.area_id+"--");
        myPresenter.PHomeSousuols(MainActivity.user_id);
        recyclerView.setLayoutManager(new GridLayoutManager(HomeSousuoLsActivity.this,5));
        souSuoAdapter = new HomeSouSuoLsAdapter(HomeSousuoLsActivity.this,listBeans);
        recyclerView.setAdapter(souSuoAdapter);



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (TextUtils.isEmpty(editText.getText())){

                    }else {
                        Intent intent = new Intent(HomeSousuoLsActivity.this,HomeSousuoActivity.class);
                        intent.putExtra("s", editText.getText().toString());
                        startActivity(intent);
                    }
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
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onCreate(null);
    }

    @Override
    public void ShowHomeSousuoLs(Object object) {
        HomeSousuoLsBean homeSousuoLsBean = (HomeSousuoLsBean) object;
        if (homeSousuoLsBean.getCode()==0){
            listBeans.clear();
            listBeans.addAll(homeSousuoLsBean.getList());
            souSuoAdapter.notifyDataSetChanged();
        }
    }
}
