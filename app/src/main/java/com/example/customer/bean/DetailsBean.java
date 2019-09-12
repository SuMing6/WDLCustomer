package com.example.customer.bean;

import java.util.List;

public class DetailsBean {

    private int code;
    private String msg;
    private DataBean data;
    private String token;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean {

        private String shop_address;
        private String shop_phone;
        private String time_start;
        private String time_end;
        private String shop_info;
        private List<String> shop_seniority;
        private List<String> shop_img;

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public String getShop_phone() {
            return shop_phone;
        }

        public void setShop_phone(String shop_phone) {
            this.shop_phone = shop_phone;
        }

        public String getTime_start() {
            return time_start;
        }

        public void setTime_start(String time_start) {
            this.time_start = time_start;
        }

        public String getTime_end() {
            return time_end;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }

        public String getShop_info() {
            return shop_info;
        }

        public void setShop_info(String shop_info) {
            this.shop_info = shop_info;
        }

        public List<String> getShop_seniority() {
            return shop_seniority;
        }

        public void setShop_seniority(List<String> shop_seniority) {
            this.shop_seniority = shop_seniority;
        }

        public List<String> getShop_img() {
            return shop_img;
        }

        public void setShop_img(List<String> shop_img) {
            this.shop_img = shop_img;
        }
    }
}
