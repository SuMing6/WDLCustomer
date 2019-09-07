package com.example.customer.view.homepage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.adapter.OnclickEightAdapter;
import com.example.customer.bean.HomepageOnclickEightBean;
import com.example.customer.contract.MyContract;
import com.example.customer.fragment.HomePageFragment;
import com.example.customer.presenter.MyPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomePageOnclickEightActivity extends AppCompatActivity implements MyContract.MyView.HomePageOnclickEightActivity {

    private int id;
    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    List<HomepageOnclickEightBean.DataBean.ListBean> listBeans = new ArrayList<>();
    List<HomepageOnclickEightBean.DataBean.AdvBean> advBeans = new ArrayList<>();
    private OnclickEightAdapter eightAdapter;
    private Banner banner;

    List<String> bannerList = new ArrayList<String>();
    private RecyclerView recyclerView;
    private RelativeLayout imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XNAJ();
        setContentView(R.layout.activity_home_page_onclick_eight);
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        //  返回
        back();
        //数据展示
        data();
        //轮播图
        bne();






    }

    private void bne() {
        banner = findViewById(R.id.home_page_onclick_eight_Banner);
    }

    private void data() {
        recyclerView = findViewById(R.id.home_page_onclick_eight_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (id==8){
            myPresenter.ShowHomePageOnclickEight(MainActivity.longitude+"",MainActivity.latitude+"", HomePageFragment.province_id,HomePageFragment.city_id,HomePageFragment.area_id,
                    0,2,id,0,0,0);
        }else {
            myPresenter.ShowHomePageOnclickEight(MainActivity.longitude+"",MainActivity.latitude+"", HomePageFragment.province_id,HomePageFragment.city_id,HomePageFragment.area_id,
                    0,1,id,0,0,0);
            //Log.e("巴图",MainActivity.longitude+"-经纬-"+MainActivity.latitude+"省市区"+HomePageFragment.province_id+"--"+HomePageFragment.city_id+"--"+HomePageFragment.area_id+"--id"+id);

        }
        eightAdapter = new OnclickEightAdapter(this,listBeans);
        recyclerView.setAdapter(eightAdapter);

    }

    private void back() {
        TextView textView = findViewById(R.id.home_page_onclick_eight_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void ShowHomePageOnclickEight(Object object) {
        HomepageOnclickEightBean homepageOnclickEightBean = (HomepageOnclickEightBean) object;
        //Log.e("巴图",homepageOnclickEightBean.getCode()+"");
        if (homepageOnclickEightBean.getCode()==0){
            listBeans.addAll(homepageOnclickEightBean.getData().getList());
            if (homepageOnclickEightBean.getData().getAdv()!=null){
                advBeans.addAll(homepageOnclickEightBean.getData().getAdv());
                for (int i = 0; i < advBeans.size(); i++) {
                    bannerList.add(advBeans.get(i).getPic());
                }
                //Log.e("轮播图e",bannerList.toString());
                banner.isAutoPlay(true).setDelayTime(2500).setImages(bannerList).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                }).start();
            }else {
                banner.setVisibility(View.GONE);
            }
            if(homepageOnclickEightBean.getData().getList().size()==0){
                recyclerView.setVisibility(View.GONE);
                imageView = findViewById(R.id.home_page_onclick_eight_imageView);
                imageView.setVisibility(View.VISIBLE);

                Log.e("轮播图e",homepageOnclickEightBean.getData().getList()+"");
            }
            eightAdapter.notifyDataSetChanged();
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
