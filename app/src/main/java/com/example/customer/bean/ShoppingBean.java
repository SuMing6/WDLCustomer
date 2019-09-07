package com.example.customer.bean;

import java.util.List;

public class ShoppingBean {

    private int code;
    private String msg;
    private String token;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private int id;
        private String shop;
        private List<GoodBean> good;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShop() {
            return shop;
        }

        public void setShop(String shop) {
            this.shop = shop;
        }

        public List<GoodBean> getGood() {
            return good;
        }

        public void setGood(List<GoodBean> good) {
            this.good = good;
        }

        public static class GoodBean {

            private String good_img;
            private String good_money;
            private int good_num;

            public String getGood_img() {
                return good_img;
            }

            public void setGood_img(String good_img) {
                this.good_img = good_img;
            }

            public String getGood_money() {
                return good_money;
            }

            public void setGood_money(String good_money) {
                this.good_money = good_money;
            }

            public int getGood_num() {
                return good_num;
            }

            public void setGood_num(int good_num) {
                this.good_num = good_num;
            }
        }
    }
}
