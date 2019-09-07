package com.example.customer.presenter;

import android.util.Log;

import com.example.customer.bean.AddDzBean;
import com.example.customer.bean.AddSoppingCarBean;
import com.example.customer.bean.CarInfoTiJiaoBean;
import com.example.customer.bean.DzBean;
import com.example.customer.bean.EightBean;
import com.example.customer.bean.HomaPageDzBean;
import com.example.customer.bean.HomeBannerBean;
import com.example.customer.bean.HomeCarInforBean;
import com.example.customer.bean.HomeGoodsBean;
import com.example.customer.bean.HomeGoodsCarBean;
import com.example.customer.bean.HomeGoodsListBean;
import com.example.customer.bean.HomePageSanBean;
import com.example.customer.bean.HomeTBean;
import com.example.customer.bean.HomepageOnclickEightBean;
import com.example.customer.bean.LoginBean;
import com.example.customer.bean.MyDzBean;
import com.example.customer.bean.MyScAddBean;
import com.example.customer.bean.MyScDataBean;
import com.example.customer.bean.MySheZhiNameBean;
import com.example.customer.bean.MySheZhiPwdBean;
import com.example.customer.bean.MySheZhiSexBean;
import com.example.customer.bean.MySocketBean;
import com.example.customer.bean.MyTicketHbBean;
import com.example.customer.bean.MyTicketKqBean;
import com.example.customer.bean.NearbyBean;
import com.example.customer.bean.OrderBean;
import com.example.customer.bean.OrderMyViewBean;
import com.example.customer.bean.RegisterBean;
import com.example.customer.bean.SanBean;
import com.example.customer.bean.ShoppingBean;
import com.example.customer.bean.ShoppingDelBean;
import com.example.customer.bean.SubmissionHBBean;
import com.example.customer.bean.SubmissionTimeBean;
import com.example.customer.bean.SubmissionYHBean;
import com.example.customer.bean.UserInfoBean;
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
        map.put("province", province);
        map.put("city", city);
        map.put("area", area);
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
        Log.e("轮播图w",cid+"");
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

    @Override
    public void PHomepageT(int cid) {
        final MyContract.MyView.HomePageFragment homePageFragment = (MyContract.MyView.HomePageFragment) t;
        map.put("cid", String.valueOf(cid));
        Log.e("轮播图e",cid+"");
        myModel.doPost(Port.HomePageT, HomeTBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homePageFragment.ShowHomePageT(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PHomepageSan() {
        final MyContract.MyView.HomePageFragment homePageFragment = (MyContract.MyView.HomePageFragment) t;
        myModel.doGet(Port.HomePageSan, HomePageSanBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homePageFragment.ShowHomePageSan(o);
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
    //详情页添加购物车
    @Override
    public void PHomePageGoods(int id, int gid, int sid, int num, int uid, String token) {
        final MyContract.MyView.HomeGoodsFragment homepageGoodsActivity = (MyContract.MyView.HomeGoodsFragment) t;
        map.put("id", String.valueOf(id));
        map.put("gid", String.valueOf(gid));
        map.put("sid", String.valueOf(sid));
        map.put("num", String.valueOf(num));
        map.put("uid", String.valueOf(uid));
        map.put("token", String.valueOf(token));
        myModel.doPost(Port.HomePageGoodsAdd, AddSoppingCarBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homepageGoodsActivity.ShowHomePageGoods(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //购物车
    @Override
    public void PHomePageGoodsCar(int id, int uid, String token) {
        final MyContract.MyView.HomepageGoodsActivity homepageGoodsActivity = (MyContract.MyView.HomepageGoodsActivity) t;
        map.put("id", String.valueOf(id));
        map.put("uid", String.valueOf(uid));
        map.put("token", String.valueOf(token));
        myModel.doPost(Port.HomePageGoodsCar, HomeGoodsCarBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homepageGoodsActivity.ShowHomePageGoodsCar(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void ShowHomePageOnclickEight(String lon, String lat, int province, int city, int area, int start, int shop_cate, int cate_id, int delivery, int coupon, int order) {
        final MyContract.MyView.HomePageOnclickEightActivity homePageOnclickEightActivity = (MyContract.MyView.HomePageOnclickEightActivity) t;
        map.put("lon", String.valueOf(lon));
        map.put("lat", String.valueOf(lat));
        map.put("province", String.valueOf(province));
        map.put("city", String.valueOf(city));
        map.put("area", String.valueOf(area));
        map.put("start", String.valueOf(start));
        map.put("shop_cate", String.valueOf(shop_cate));
        map.put("cate_id", String.valueOf(cate_id));
        map.put("delivery", String.valueOf(delivery));
        map.put("coupon", String.valueOf(coupon));
        map.put("order", String.valueOf(order));
        myModel.doPost(Port.HomePageOnclickEight, HomepageOnclickEightBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homePageOnclickEightActivity.ShowHomePageOnclickEight(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PHB(int id, int uid, String token) {
        final MyContract.MyView.SubmissionHBActivity submissionHBActivity = (MyContract.MyView.SubmissionHBActivity) t;
        map.put("id", String.valueOf(id));
        map.put("uid", String.valueOf(uid));
        map.put("token", String.valueOf(token));
        myModel.doPost(Port.SubmissionHB, SubmissionHBBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                submissionHBActivity.ShowSubmissionHB(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PYH(int id, int uid, String token) {
        final MyContract.MyView.SubmissionYHActivity submissionYHActivity = (MyContract.MyView.SubmissionYHActivity) t;
        map.put("id", String.valueOf(id));
        map.put("uid", String.valueOf(uid));
        map.put("token", String.valueOf(token));
        myModel.doPost(Port.SubmissionYH, SubmissionYHBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                submissionYHActivity.ShowSubmissionYH(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    //地址
    @Override
    public void PSubmissionDZ(int id, String token) {
        final MyContract.MyView.SubmissionDZActivity submissionDZActivity = (MyContract.MyView.SubmissionDZActivity) t;
        map.put("id", String.valueOf(id));
        map.put("token", String.valueOf(token));
        myModel.doPost(Port.SubmissionDZ, DzBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                submissionDZActivity.ShowSubmissionDZ(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PSubmissionTime() {
        final MyContract.MyView.HomeGoodsSubmissionActivity homeGoodsSubmissionActivity = (MyContract.MyView.HomeGoodsSubmissionActivity) t;
        myModel.doPost(Port.SubmissionTime, SubmissionTimeBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homeGoodsSubmissionActivity.ShowSubmissionTime(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PSubmissionInfo(int id, int uid, String token, int rid, int cid, int aid) {
        final MyContract.MyView.HomeGoodsSubmissionActivity homeGoodsSubmissionActivity = (MyContract.MyView.HomeGoodsSubmissionActivity) t;
        map.put("id", String.valueOf(id));
        map.put("uid", String.valueOf(uid));
        map.put("token", String.valueOf(token));
        map.put("rid", String.valueOf(rid));
        map.put("cid", String.valueOf(cid));
        map.put("aid", String.valueOf(aid));
        myModel.doPost(Port.SubmissionInfo, HomeCarInforBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homeGoodsSubmissionActivity.ShowSubmissionInfo(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PSubmissionTiJiao(int id, int uid, String token, int rid, int cid, int aid, String info, int zid, String time) {
        final MyContract.MyView.HomeGoodsSubmissionActivity homeGoodsSubmissionActivity = (MyContract.MyView.HomeGoodsSubmissionActivity) t;
        map.put("id", String.valueOf(id));
        map.put("uid", String.valueOf(uid));
        map.put("token", String.valueOf(token));
        map.put("rid", String.valueOf(rid));
        map.put("cid", String.valueOf(cid));
        map.put("aid", String.valueOf(aid));
        map.put("info", String.valueOf(info));
        map.put("zid", String.valueOf(zid));
        map.put("time", String.valueOf(time));
        myModel.doPost(Port.SubmissionTiJiao, CarInfoTiJiaoBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homeGoodsSubmissionActivity.ShowSubmissionTiJiao(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PShoppingFragment(int id, String token) {
        final MyContract.MyView.ShoppingFragment shoppingFragment = (MyContract.MyView.ShoppingFragment) t;
        map.put("id", String.valueOf(id));
        map.put("token", String.valueOf(token));
        myModel.doPost(Port.ShoppingData, ShoppingBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                shoppingFragment.ShowShoppingData(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PShoppingDel(int id, int uid , String token) {
        final MyContract.MyView.ShoppingFragment shoppingFragment = (MyContract.MyView.ShoppingFragment) t;
        map.put("id", String.valueOf(id));
        map.put("uid", String.valueOf(uid));
        map.put("token", String.valueOf(token));
        myModel.doPost(Port.ShoppingDatadel, ShoppingDelBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                shoppingFragment.ShowShoppingDataDel(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }


    //订单页面
    @Override
    public void POrderFragment(int id, String token) {
        final MyContract.MyView.OrderFragment orderFragment = (MyContract.MyView.OrderFragment) t;
        map.put("id", String.valueOf(id));
        map.put("token", String.valueOf(token));
        myModel.doPost(Port.OrderFragment, OrderBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                orderFragment.ShowOrderFragment(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }


    //评论
    @Override
    public void POrderMyView(int id, String token,int order,int score,String info) {
        final MyContract.MyView.OrderMyViewz orderMyViewz = (MyContract.MyView.OrderMyViewz) t;
        map.put("id", String.valueOf(id));
        map.put("token", String.valueOf(token));
        map.put("order", String.valueOf(order));
        map.put("score", String.valueOf(score));
        map.put("info", String.valueOf(info));
        myModel.doPost(Port.OrderMyviewFragment, OrderMyViewBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                orderMyViewz.ShowOrderMyView(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    //我的页面详情
    @Override
    public void PMyInfo(int id, String token) {
        final MyContract.MyView.MyFragment myFragment = (MyContract.MyView.MyFragment) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        myModel.doPost(Port.MyUserInfo, UserInfoBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                myFragment.ShowMyInfo(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //设置页面
    @Override
    public void PMyInfozhe(int id, String token) {
        final MyContract.MyView.MySheZhiActivity mySheZhiActivity = (MyContract.MyView.MySheZhiActivity) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        myModel.doPost(Port.MyUserInfo, UserInfoBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                mySheZhiActivity.ShowMySheZhiif(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //修改性别
    @Override
    public void PMyInfozhesex(int id, String token, int sex) {
        final MyContract.MyView.MySheZhiActivity mySheZhiActivity = (MyContract.MyView.MySheZhiActivity) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        map.put("sex", String.valueOf(sex));
        myModel.doPost(Port.MyShezhiSex, MySheZhiSexBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                mySheZhiActivity.ShowMySheZhiSex(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //设置昵称
    @Override
    public void PMyShezhiName(int id, String token,String nickname) {
        final MyContract.MyView.MySheZhiNameActivity mySheZhiNameActivity = (MyContract.MyView.MySheZhiNameActivity) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        map.put("nickname",nickname);
        myModel.doPost(Port.MySheZhiName, MySheZhiNameBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                mySheZhiNameActivity.ShowMyShezhiName(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //设置密码
    @Override
    public void PMyShezhiPwd(int id, String token, int code, String password, String sure_password) {
        final MyContract.MyView.MySheZhiPwdActivity MySheZhiPwdActivity = (MyContract.MyView.MySheZhiPwdActivity) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        map.put("code", String.valueOf(code));
        map.put("password", String.valueOf(password));
        map.put("sure_password", String.valueOf(sure_password));
        myModel.doPost(Port.MySheZhiPwd, MySheZhiPwdBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                MySheZhiPwdActivity.ShowMySheZhiPwd(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    //我的页面地址列表
    @Override
    public void PMyDz(int id, String token) {
        final MyContract.MyView.MyDzActivity MyDzActivity = (MyContract.MyView.MyDzActivity) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        myModel.doPost(Port.MyDz, MyDzBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                MyDzActivity.ShowMyDz(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PAddDz(int id, String token, String name, String phone, String weixin, int province, int city, int district, String address, int aid, String lon, String lat) {
        final MyContract.MyView.MyDzAddActivity myDzAddActivity = (MyContract.MyView.MyDzAddActivity) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        map.put("name",name);
        map.put("phone",phone);
        map.put("weixin",weixin);
        map.put("province", String.valueOf(province));
        map.put("city", String.valueOf(city));
        map.put("district", String.valueOf(district));
        map.put("address",address);
        map.put("aid", String.valueOf(aid));
        map.put("lon",lon);
        map.put("lat",lat);
        myModel.doPost(Port.AddDz, AddDzBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                myDzAddActivity.ShowAddDz(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PAddDzSSQ() {
        final MyContract.MyView.MyDzAddActivity myDzAddActivity = (MyContract.MyView.MyDzAddActivity) t;
        myModel.doGet(Port.san, SanBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                myDzAddActivity.ShowAddDzSSQ(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PMyScData(int id, String token, String lon, String lat) {
        final MyContract.MyView.MyScActivity myScActivity = (MyContract.MyView.MyScActivity) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        map.put("lon",lon);
        map.put("lat",lat);
        myModel.doPost(Port.ScData, MyScDataBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                myScActivity.ShowMyScData(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }
    @Override
    public void PMyScDataDel(int id, String token, int mid) {
        final MyContract.MyView.MyScActivity myScActivity = (MyContract.MyView.MyScActivity) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        map.put("mid", String.valueOf(mid));
        myModel.doPost(Port.ScDataDel, MyScAddBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                myScActivity.PMyScDataDel(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PMyTicketHBcData(int id, String token, int type) {
        final MyContract.MyView.MyTicketHBFragment myScActivity = (MyContract.MyView.MyTicketHBFragment) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        map.put("type", String.valueOf(type));
        myModel.doPost(Port.MyTicketHBcData, MyTicketHbBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                myScActivity.ShowMyTicketHBcData(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PMyTicketKQcData(int id, String token, int type) {
        final MyContract.MyView.MyTicketKQFragment myScActivity = (MyContract.MyView.MyTicketKQFragment) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        map.put("type", String.valueOf(type));
        myModel.doPost(Port.MyTicketKQcData, MyTicketKqBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                myScActivity.ShowMyTicketKQcData(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

    @Override
    public void PMySocket(int id, String token) {
        final MyContract.MyView.HomePageMessageCustomer homePageMessageCustomer = (MyContract.MyView.HomePageMessageCustomer) t;
        map.put("id", String.valueOf(id));
        map.put("token",token);
        myModel.doPost(Port.MySocket, MySocketBean.class, map, new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                homePageMessageCustomer.ShowSocket(o);
            }
            @Override
            public void onError(Object o) {

            }
        });
    }

}
