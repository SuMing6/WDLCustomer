package com.example.customer.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.example.customer.R;
import com.example.customer.activity.MainActivity;
import com.example.customer.adapter.HomePageEightAdapter;
import com.example.customer.adapter.HomePageNearbyAdapter;
import com.example.customer.bean.EightBean;
import com.example.customer.bean.HomaPageDzBean;
import com.example.customer.bean.HomeBannerBean;
import com.example.customer.bean.HomeGoodsBean;
import com.example.customer.bean.NearbyBean;
import com.example.customer.contract.MyContract;
import com.example.customer.presenter.MyPresenter;
import com.example.customer.view.homepage.HomePageMessageActivity;
import com.example.customer.view.homepage.HomepageGoodsActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomePageFragment extends Fragment implements MyContract.MyView.HomePageFragment{

    public static View view;

    MyContract.MyPresenter myPresenter = new MyPresenter<>(this);
    private int province_id;
    private int city_id;
    private int area_id;
    List<NearbyBean.DataBean> nearbyBeans = new ArrayList<>();
    List<EightBean.DataBean> eightBean = new ArrayList<>();
    private HomePageNearbyAdapter adapter;
    private HomePageEightAdapter eightAdapter;
    private Banner banner;
    List<HomeBannerBean.DataBean> beanList = new ArrayList<>();
    List<String> integerList = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myPresenter.PHomepageDz(MainActivity.province,MainActivity.city,MainActivity.district);
        //滑动冲突
        huaDong();
        //定位
        weizhi();
        //输入框焦点
        editText();
        //消息页面
        message();
        //附近商家
        fujinshangjia();
        //首页8图
        eight();
        //首页轮播
        banner();
        //商品详情
        goods();




    }

    private void goods() {
        adapter.setSetOnClickItem(new HomePageNearbyAdapter.setOnClickItem() {
            @Override
            public void onGreat(int id) {
                Intent intent = new Intent(getContext(), HomepageGoodsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });
    }

    private void banner() {
        banner = view.findViewById(R.id.homepage_Banner);
        myPresenter.PHomepageBanner(2);
    }

    private void eight() {
        RecyclerView recyclerView = view.findViewById(R.id.homepage_eight);
        myPresenter.PHomepageEight();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));

        eightAdapter = new HomePageEightAdapter(getActivity(),eightBean);
        recyclerView.setAdapter(eightAdapter);

    }

    private void fujinshangjia() {
        XRecyclerView xRecyclerView = view.findViewById(R.id.homepage_XRecyclerView);

        myPresenter.PHomepageNearby(MainActivity.longitude+"",MainActivity.latitude+"",province_id,city_id,area_id,0);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        xRecyclerView.setLayoutManager(linearLayoutManager);

        adapter = new HomePageNearbyAdapter(getActivity(),nearbyBeans);
        xRecyclerView.setAdapter(adapter);


    }

    private void message() {
        ImageView imageView = view.findViewById(R.id.homepage_xiaoxi);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomePageMessageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void editText() {
        LinearLayout linearLayout = view.findViewById(R.id.homepage_activity);
        final EditText editText = view.findViewById(R.id.homepage_shousuo);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setFocusable(false);
                editText.clearFocus();
            }
        });

        editText.setFocusable(false);
        editText.clearFocus();
        //输入框获取焦点
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
            }
        });
    //点击软键盘外部，收起软键盘
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager manager = ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        final NestedScrollView scrollView = view.findViewById(R.id.homepage_ScrollView);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.setFocusable(true);
                scrollView.setFocusableInTouchMode(true);
                scrollView.requestFocus();
                HideKeyboard(getView());
                return false;
            }
        });

    }


    @Override
    public void ShowHomePageNearby(Object object) {
        NearbyBean nearbyBean = (NearbyBean) object;
        if (nearbyBean.getCode()== 0){
            nearbyBeans.addAll(nearbyBean.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ShowHomePageDz(Object object) {
        HomaPageDzBean homaPageDzBean = (HomaPageDzBean) object;
        if (homaPageDzBean.getCode() == 0){
            province_id = homaPageDzBean.getInfo().getProvince_id();
            city_id = homaPageDzBean.getInfo().getCity_id();
            area_id = homaPageDzBean.getInfo().getArea_id();
        }

    }

    @Override
    public void ShowHomePageEight(Object object) {
        EightBean eightBean = (EightBean) object;
        if (eightBean.getCode() == 0){
            this.eightBean.addAll(eightBean.getData());
            eightAdapter.notifyDataSetChanged();
        }else {
        }
    }

    @Override
    public void ShowHomePageBanner(Object object) {
        HomeBannerBean homeBannerBean = (HomeBannerBean) object;

        if (homeBannerBean.getCode() == 0){
            //Log.e("数据v",o.toString());
            beanList.addAll(homeBannerBean.getData());
            for (int i = 0; i < beanList.size(); i++) {
                integerList.add(beanList.get(i).getPic());
            }
            //Log.e("轮播图",integerList.toString());
            banner.isAutoPlay(true).setDelayTime(2500).setImages(integerList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            }).start();
        }else {

        }
    }

    /*-----------------------------------------------------------------------------------------------------*/

    private void weizhi() {
        TextView textView = view.findViewById(R.id.homepage_dingwei);
        if (MainActivity.district!= null){
            textView.setText(MainActivity.district);
        }else {
            textView.setText("定位中....");
        }
    }

    //点击NestedScrollView收起输入框
    public static void HideKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm.isActive( ) ) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );
        } }

    private void huaDong() {
        NestedScrollView scrollView = view.findViewById(R.id.homepage_ScrollView);
        //解决滑动冲突
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int page = 0 ;
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    //底部加载
                    page++;
                    //myPresenter.PHomePageGoods();
                }
            }
        });
    }

}
