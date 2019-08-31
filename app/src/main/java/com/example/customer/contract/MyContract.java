package com.example.customer.contract;


import java.util.Map;

public interface MyContract {
    interface MyModel{
        void doGet(String url, Class cls, Map<String, String> map, com.example.customer.model.MyModel.MyCallBack myCallBack);
        void doPost(String url, Class cls, Map<String, String> map, com.example.customer.model.MyModel.MyCallBack myCallBack);
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
        //首页详情
        void PHomepageGoods(int id);
        //首页详情
        void PHomepageGoodsF(int id);
        //商品详情列表
        void PHomepageGoodsL(int id,int cid);
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
        }
        interface HomepageGoodsActivity{
            void ShowHomePageGoods(Object object);
        }
        interface HomeGoodsFragment{
            void ShowHomePageGoodsF(Object object);
            void ShowHomePageGoodsL(Object object);
        }
    }
}
