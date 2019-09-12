package com.example.customer.view.homegoods;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.activity.PasswordJiami;
import com.example.customer.adapter.HomeGoodsFragmentAdapter;
import com.example.customer.adapter.HomeGoodsRightAdapter;
import com.example.customer.adapter.HomeGoodsSousuoAdapter;
import com.example.customer.adapter.HomeGoodseFragmentAdapter;
import com.example.customer.bean.HomeGoodsListBean;
import com.example.customer.bean.HomePageGoodSousuoBean;
import com.example.customer.bean.HomePageGoodSousuoCarBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homepage.HomepageGoodsActivity;

import java.util.ArrayList;
import java.util.List;

public class HomepageGoodssousuoActivity extends AppCompatActivity implements MyContract.MyView.HomepageGoodssousuoActivity {

    List<HomePageGoodSousuoBean.DataBean> dataBeans = new ArrayList<>();
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private HomeGoodsSousuoAdapter sousuoAdapter;
    private View inflate;

    public static int gid ;
    private PopupWindow popupWindow;
    private HomePageGoodSousuoBean homePageGoodSousuoBean;

    private int zs ;
    private int sid ;
    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_homepage_goodssousuo);
        Intent intent = getIntent();
        String n = intent.getStringExtra("n");
        //Log.e("搜索1",HomepageGoodsActivity.id+n);
        myPresenter.PHomePageGoodsSousuo(HomepageGoodsActivity.id,n);

        recyclerView = findViewById(R.id.home_goods_sousuo_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        sousuoAdapter = new HomeGoodsSousuoAdapter(HomepageGoodssousuoActivity.this,dataBeans);
        recyclerView.setAdapter(sousuoAdapter);
        relativeLayout = findViewById(R.id.home_goods_sousuo_RelativeLayout);

        inflate = LayoutInflater.from(HomepageGoodssousuoActivity.this).inflate(R.layout.popup_window_homepage_sousuo, null);
        sousuoAdapter.setSetOnClickItem(new HomeGoodsSousuoAdapter.setOnClickItem() {
            @Override
            public void onGreat(int id,int ggid) {
                //Log.e("啊大大",""+id+homePageGoodSousuoBean.getData().get(id).getTitle());
                gid = ggid ;
                LinearLayout linearLayout =  findViewById(R.id.fragment_home_goods_sousuo_LinearLayout);
                popupWindow = new PopupWindow(inflate, linearLayout.getHeight(), 1100, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setTouchable(true);
                popupWindow.showAsDropDown(relativeLayout,0 ,0 );

                TextView name = inflate.findViewById(R.id.popup_window_homepage_goods_sousuo_name);
                final TextView money = inflate.findViewById(R.id.popup_window_homepage_goods_sousuo_money);
                RelativeLayout jian = inflate.findViewById(R.id.popup_window_homepage_goods_sousuo_jian);
                RelativeLayout jia = inflate.findViewById(R.id.popup_window_homepage_goods_sousuo_jia);
                final TextView sum = inflate.findViewById(R.id.popup_window_homepage_goods_sousuo_sum);
                RecyclerView recyclerView = inflate.findViewById(R.id.popup_window_homepage_goods_sousuo_RecyclerView);
                Button ok = inflate.findViewById(R.id.popup_window_homepage_goods_sousuo_ok);
                name.setText(homePageGoodSousuoBean.getData().get(id).getTitle());

                //money.setText(goodsListBean1.getData().get(id).getSpec().get(1).getSpec_money());

                //横向发展
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomepageGoodssousuoActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                List<HomePageGoodSousuoBean.DataBean.SpecBean> spec = homePageGoodSousuoBean.getData().get(id).getSpec();

                HomeGoodseFragmentAdapter fragmentAdapter = new HomeGoodseFragmentAdapter(HomepageGoodssousuoActivity.this,spec);
                recyclerView.setAdapter(fragmentAdapter);
                fragmentAdapter.notifyDataSetChanged();
                //规格适配器
                fragmentAdapter.setSetOnClickItem(new HomeGoodseFragmentAdapter.setOnClickItem() {
                    @Override
                    public void onGreat(final int eid,String e) {
                        //Toast.makeText(getContext(),""+eid,Toast.LENGTH_SHORT).show();
                        sid = eid ;
                        money.setText(e);
                    }
                });
                fragmentAdapter.notifyDataSetChanged();
                jia.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        zs= Integer.parseInt(sum.getText().toString());
                        zs = zs+1;
                        sum.setText(zs+"");/*
                        mo= Double.parseDouble(money.getText().toString());
                        aa = zs*mo;
                        money.setText(aa+"");*/

                    }
                });
                jian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zs= Integer.parseInt(sum.getText().toString());
                        if (zs!=0){
                            zs = zs-1;
                            sum.setText(zs+"");/*
                            mo= Double.parseDouble(money.getText().toString());
                            aa = zs*mo;
                            money.setText(aa+"");*/
                        }
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String s = MainActivity.token;
                        String passwjiemi = PasswordJiami.passwjiemi(s);
                        String passwordjiami = PasswordJiami.passwordjiami(passwjiemi);
                        String s1 = sum.getText().toString();
                        myPresenter.PHomePageGoodsSousuoCar(HomepageGoodsActivity.id ,gid,sid,Integer.parseInt(s1), MainActivity.user_id,passwordjiami);
                        //Log.e("啊大大",HomepageGoodsActivity.id+"--"+gid+"--"+sid+"--"+Integer.parseInt(s1)+"--"+MainActivity.user_id+"--"+passwordjiami);
                        //myPresenter.PHomePageGoodsCar(MainActivity.user_id,passwordjiami,);
                    }
                });


            }
        });
        //无规格
        sousuoAdapter.setSetOnCArClickItem(new HomeGoodsSousuoAdapter.setOnClickIteme() {
            @Override
            public void onGreat(int id,int ggid) {
                //Log.e("啊大大",""+id);
            }
        });

        ImageView imageView = findViewById(R.id.home_goods_sousuo_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @Override
    public void ShowHomePageGoodsSousuo(Object object) {
        homePageGoodSousuoBean = (HomePageGoodSousuoBean) object;
        //Log.e("搜索2", homePageGoodSousuoBean.getCode()+"");
        if (homePageGoodSousuoBean.getCode()==0){
            dataBeans.addAll(homePageGoodSousuoBean.getData());
            sousuoAdapter.notifyDataSetChanged();
           // Log.e("搜索3",dataBeans.get(0).getTitle());
        }
    }

    @Override
    public void ShowHomePageGoodsSousuoCar(Object object) {
        HomePageGoodSousuoCarBean homePageGoodSousuoCarBean = (HomePageGoodSousuoCarBean) object;
        if (homePageGoodSousuoCarBean.getCode()==0){
            Toast.makeText(HomepageGoodssousuoActivity.this,homePageGoodSousuoCarBean.getMsg(),Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /*-------------------------------------------------------------------------------------------------*/
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
