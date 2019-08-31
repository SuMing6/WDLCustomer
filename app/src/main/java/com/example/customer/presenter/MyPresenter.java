package com.example.customer.presenter;

import com.example.customer.bean.EightBean;
import com.example.customer.bean.HomaPageDzBean;
import com.example.customer.bean.HomeBannerBean;
import com.example.customer.bean.HomeGoodsBean;
import com.example.customer.bean.HomeGoodsListBean;
import com.example.customer.bean.LoginBean;
import com.example.customer.bean.NearbyBean;
import com.example.customer.bean.RegisterBean;
import com.example.customer.bean.YAMBean;
import com.example.customer.contract.MyContract;
import com.example.customer.data.Port;
import com.example.customer.model.MyModel;

import java.util.HashMap;
import java.util.Map;

public class MyPresenter<T> implements MyContract.MyPresenter {

    MyContract.MyModel myModel ;
    T t ;
    private final Map<String, String> map;

    public MyPresenter(T tt){
        t = tt ;
        this.map = new HashMap<>();
        myModel = new MyModel();
    }

    //获取验证码
    @Override
    public void PMainactivityYzm(String phone) {
        final MyContract.MyView.MainActivity  mainActivity = (MyContract.MyView.MainActivity) t;
        map.put("phone", phone);
        myModel.doPost(Port.mainActivityYZM, YAMBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                mainActivity.MainactivityYzm(o);
            }

            @Override
            public void onError(Object o) {

            }
        });
    }
        //注册页面验证码
    @Override
    public void PRegisteractivityYzm(String phone) {
        final MyContract.MyView.RegisterActivity registerActivity = (MyContract.MyView.RegisterActivity) t;
        map.put("phone", phone);
        myModel.doPost(Port.mainActivityYZM, YAMBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                registerActivity.RegisterYzm(o);
            }

            @Override
            public void onError(Object o) {

            }
        });
    }

    //登录
    @Override
    public void PMainactivityLogin(String phone, int code) {
        final MyContract.MyView.MainActivity  mainActivity = (MyContract.MyView.MainActivity) t;
        map.put("phone", phone);
        map.put("code", String.valueOf(code));
        myModel.doPost(Port.mainActivityLogin, LoginBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                mainActivity.MainactivityLogin(o);
            }

            @Override
            public void onError(Object o) {

            }
        });
    }
    //注册
    @Override
    public void PRegister(String phone, int code) {
        final MyContract.MyView.RegisterActivity registerActivity = (MyContract.MyView.RegisterActivity) t;
        map.put("phone", phone);
        map.put("code", String.valueOf(code));
        myModel.doPost(Port.mainActivityRegister, RegisterBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                registerActivity.ShowRegisterR(o);
            }

            @Override
            public void onError(Object o) {

            }
        });
    }

    // 首页地址信息
    @Override
    public void PHomepageDz(String province, String city, String area) {
        final MyContract.MyView.HomePageFragment homePageFragment = (MyContract.MyView.HomePageFragment) t;
        map.put("lon", province);
        map.put("lat", city);
        map.put("start", area);
        myModel.doPost(Port.HomePageDz, HomaPageDzBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homePageFragment.ShowHomePageDz(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //首页附近商店
    @Override
    public void PHomepageNearby(String lon, String lat, int province, int city, int area, int start) {
        final MyContract.MyView.HomePageFragment homePageFragment = (MyContract.MyView.HomePageFragment) t;
        map.put("lon", lon);
        map.put("lat", lat);
        map.put("province", String.valueOf(province));
        map.put("city", String.valueOf(city));
        map.put("area", String.valueOf(area));
        map.put("start", String.valueOf(start));
        myModel.doPost(Port.HomePageNearby, NearbyBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homePageFragment.ShowHomePageNearby(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //首页8图片
    @Override
    public void PHomepageEight() {
        final MyContract.MyView.HomePageFragment homePageFragment = (MyContract.MyView.HomePageFragment) t;
        myModel.doPost(Port.HomePageEight, EightBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homePageFragment.ShowHomePageEight(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    //首页轮播图
    @Override
    public void PHomepageBanner(int cid) {
        final MyContract.MyView.HomePageFragment homePageFragment = (MyContract.MyView.HomePageFragment) t;
        map.put("cid", String.valueOf(cid));
        myModel.doPost(Port.HomePageBanner, HomeBannerBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homePageFragment.ShowHomePageBanner(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //首页详情s
    @Override
    public void PHomepageGoods(int id) {
        final MyContract.MyView.HomepageGoodsActivity homepageGoodsActivity = (MyContract.MyView.HomepageGoodsActivity) t;
        map.put("id", String.valueOf(id));
        myModel.doPost(Port.HomePageGoods, HomeGoodsBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homepageGoodsActivity.ShowHomePageGoods(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //详情页面F
    @Override
    public void PHomepageGoodsF(int id) {
        final MyContract.MyView.HomeGoodsFragment homepageGoodsActivity = (MyContract.MyView.HomeGoodsFragment) t;
        map.put("id", String.valueOf(id));
        myModel.doPost(Port.HomePageGoods, HomeGoodsBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homepageGoodsActivity.ShowHomePageGoodsF(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //详情页面列表
    @Override
    public void PHomepageGoodsL(int id, int cid) {
        final MyContract.MyView.HomeGoodsFragment homepageGoodsActivity = (MyContract.MyView.HomeGoodsFragment) t;
        map.put("id", String.valueOf(id));
        map.put("cid", String.valueOf(cid));
        myModel.doPost(Port.HomePageGoodsList, HomeGoodsListBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homepageGoodsActivity.ShowHomePageGoodsL(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
}
