package com.example.customer.contract;


import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;

public interface MyContract {
    interface MyModel{
        void doGet(String url, Class cls, Map<String, String> map, com.example.customer.model.MyModel.MyCallBack myCallBack);
        void doPost(String url, Class cls, Map<String, String> map, com.example.customer.model.MyModel.MyCallBack myCallBack);
        void doPTxPost(String url, Class cls, int id , String token	, String content , MultipartBody.Part file, com.example.customer.model.MyModel.MyCallBack myCallBack);
    }
    interface MyPresenter{
        //验证码
        void PMainactivityYzm(String phone);
        void PRegisteractivityYzm(String phone);
        //登录
        void PMainactivityLogin(String phone,int code);
        //注册
        void PRegister(String phone,int code);
        //首页地址信息
        void PHomepageDz(String province,String city,String area);
        //首页附近商店
        void PHomepageNearby(String lon,String lat,int province,int city,int area,int start);
        //首页8图
        void PHomepageEight();
        //首页轮播图
        void PHomepageBanner(int cid);
        void PHomepageT(int cid);
        void PHomepageSan();
        //
        void PHomeSousuo(String lon,String lat,int province,int city,int area ,int start , String keyword , int id);
        void PHomeSousuols(int id);
        //首页详情
        void PHomepageGoods(int id);
        //首页详情
        void PHomepageGoodsF(int id);
        //商品详情列表
        void PHomepageGoodsL(int id,int cid);
        //详情添加购物车
        void PHomePageGoods(int id,int gid,int sid,int num,int uid,String token);
        //详情页购物车
        void PHomePageGoodsCar(int id,int uid,String token);
        //详情页面搜索
        void PHomePageGoodsSousuo(int id,String keyword);
        void PHomePageGoodsSousuoCar(int id,int gid,int sid,int num,int uid,String token);
        void ShowHomePageOnclickEight(String lon,String lat,int province,int city,int area,int start,int shop_cate,int cate_id,int delivery,int coupon,int order);

        //评价列表
        void PHomePageEvaluateData(int id);

        void PHomeDetailsData(int id);




        //提交订单
        void PHB(int id,int uid,String token);
        void PYH(int id,int uid,String token);

        //地址展示
        void PSubmissionDZ(int id,String token);
        void PSubmissionTime();
        void PSubmissionInfo(int id ,int uid,String token,int rid,int cid, int aid);
        void PSubmissionTiJiao(int id ,int uid,String token,int rid,int cid, int aid,String info,int zid ,String time);


        //购物车
        void PShoppingFragment(int id,String token);
        void PShoppingDel(int id,int uid,String token);



        //订单
        void POrderFragment(int id,String token);
        void POrderMyView(int id,String token,int order,int score,String info);

        //我的页面我的信息
        void PMyInfo(int id,String token);
        //我的页面我的详细信息
        void PMyInfozhe(int id,String token);
        ///修改性别
        void PMyInfozhesex(int id,String token,int sex);
        //我的页面修改名称
        void PMyShezhiName(int id,String token,String nickname);
        //我的页面设置支付密码
        void PMyShezhiPwd(int id,String token,int code,String password, String sure_password);
        void PMyShezhiYZM(String phone);
        //我的页面上传头像
        void ShowMySheZhiTouxiang(int id, String token, String url);
        //退出登錄
        void ShowMySheZhiOutLogin(int id, String token);
        //我的页面地址列表
        void PMyDz(int id,String token);
        void PMyDzMR(int id,String token,int address_id);
        //添加地址
        void PAddDz(int id,String token,String name,String phone,String weixin,int province,int city,int district
                ,String address,int aid,String lon,String lat);
        //添加地址中的城市
        void PAddDzSSQ();
        void PMyScData(int id,String token,String lon,String lat);
        void PMyScDataDel(int id, String token, int mid);

        void ShowMyYJ(int id, String token,String content , MultipartBody.Part file);

        void PMyTicketHBcData(int id, String token, int type);
        void PMyTicketKQcData(int id, String token, int type);



        void PMySocket(int id, String token);
        void PDianDan(int id, String token,int type);
        void PTongZhi(int id, String token);
    }
    interface MyView{
        interface MainActivity{
            void MainactivityYzm(Object object);
            void MainactivityLogin(Object object);
        }
        interface RegisterActivity{
            void RegisterYzm(Object object);
            void ShowRegisterR(Object object);
        }
        interface HomePageFragment{
            void ShowHomePageNearby(Object object);
            void ShowHomePageDz(Object object);
            void ShowHomePageEight(Object object);
            void ShowHomePageBanner(Object object);
            void ShowHomePageT(Object object);
            void ShowHomePageSan(Object object);
        }
        interface HomepageGoodsActivity{
            void ShowHomePageGoods(Object object);
            void ShowHomePageGoodsCar(Object object);
        }
        //搜索
        interface HomepageGoodssousuoActivity{
            void ShowHomePageGoodsSousuo(Object object);
            void ShowHomePageGoodsSousuoCar(Object object);
        }
        //店铺评价列表
        interface HomeEvaluateFragment{
            void ShowHomePageEvaluateData(Object object);
        }
        //店铺评价列表
        interface HomeDetailsFragment{
            void HomeDetailsData(Object object);
        }
        interface HomeGoodsFragment{
            void ShowHomePageGoodsF(Object object);
            void ShowHomePageGoodsL(Object object);
            void ShowHomePageGoods(Object object);
        }
        interface HomeSousuoActivity{
            void ShowHomeSousuo(Object object);
        }
        interface HomeSousuoLsActivity{
            void ShowHomeSousuoLs(Object object);
        }
        //   8  图
        interface HomePageOnclickEightActivity{
            void ShowHomePageOnclickEight(Object object);
        }



        interface SubmissionHBActivity{
            void ShowSubmissionHB(Object object);

        }
        interface SubmissionYHActivity{
            void ShowSubmissionYH(Object object);
        }
        interface SubmissionDZActivity{
            void ShowSubmissionDZ(Object object);
        }
        //提交订单页面
        interface HomeGoodsSubmissionActivity{
            void ShowSubmissionTime(Object object);
            void ShowSubmissionInfo(Object object);
            void ShowSubmissionTiJiao(Object object);
        }
        interface ShoppingFragment{
            void ShowShoppingData(Object object);
            void ShowShoppingDataDel(Object object);
        }




        //订单
        interface OrderFragment{
            void ShowOrderFragment(Object object);
        }

        //订单评价
        interface OrderMyViewz{
            void ShowOrderMyView(Object object);
        }

        //我的页面
        interface MyFragment{
            void ShowMyInfo(Object object);
        }
        //我的设置页面
        interface MySheZhiActivity{
            void ShowMySheZhiif(Object object);
            void ShowMySheZhiSex(Object object);
            void ShowMySheZhiTouxiang(Object object);
            void ShowMySheZhiOut(Object object);
        }
        //我的设置姓名页面
        interface MySheZhiNameActivity{
            void ShowMyShezhiName(Object object);
        }
        //设置密码
        interface MySheZhiPwdActivity{
            void ShowMySheZhiPwd(Object object);
            void ShowMySheZhiYZM(Object object);
        }
        //我的页面设置地址
        interface MyDzActivity{
            void ShowMyDz(Object object);
            void ShowMyDzMR(Object object);
        }
        //我的页面设置地址
        interface MyDzAddActivity{
            void ShowAddDz(Object object);
            void ShowAddDzSSQ(Object object);
        }
        //我的页面设置地址
        interface MyScActivity{
            void ShowMyScData(Object object);
            void PMyScDataDel(Object object);
        }
        //我的页面设置地址
        interface MyTicketHBFragment{
            void ShowMyTicketHBcData(Object object);
        }
        //我的页面设置地址
        interface MyTicketKQFragment{
            void ShowMyTicketKQcData(Object object);
        }
        //我的页面意见
        interface MyYjActivity{
            void ShowMyYj(Object object);
        }
        //客服聊天
        interface HomePageMessageCustomer{
            void ShowSocket(Object object);
        }
        //首页订单消息
        interface HomePageDingDan{
            void ShowDianDan(Object object);
        }
        //首页信息
        interface HomePageMessageActivity{
            void ShowTongZhi(Object object);
        }
    }
}
