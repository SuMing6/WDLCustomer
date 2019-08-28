package com.example.customer.contract;


import java.util.Map;

public interface MyContract {
    interface MyModel{
        void doGet(String url, Class cls, Map<String, String> map, com.example.customer.model.MyModel.MyCallBack myCallBack);
        void doPost(String url, Class cls, Map<String, String> map, com.example.customer.model.MyModel.MyCallBack myCallBack);
    }
    interface MyPresenter{
        void PHomePage();
    }
    interface MyView{
        interface HomePageFragment{
            void ShowHomepage();
        }
    }
}
