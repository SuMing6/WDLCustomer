package com.example.customer.bean;

import java.util.List;

public class OrderBean {

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
        private int shop_id;
        private String user_time;
        private String money_paid;
        private int user_status;
        private int is_comment;
        private int is_zhifu;
        private int shop_status;
        private int rider_status;
        private StatusBean status;
        private ShopBean shop;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getUser_time() {
            return user_time;
        }

        public void setUser_time(String user_time) {
            this.user_time = user_time;
        }

        public String getMoney_paid() {
            return money_paid;
        }

        public void setMoney_paid(String money_paid) {
            this.money_paid = money_paid;
        }

        public int getUser_status() {
            return user_status;
        }

        public void setUser_status(int user_status) {
            this.user_status = user_status;
        }

        public int getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(int is_comment) {
            this.is_comment = is_comment;
        }

        public int getIs_zhifu() {
            return is_zhifu;
        }

        public void setIs_zhifu(int is_zhifu) {
            this.is_zhifu = is_zhifu;
        }

        public int getShop_status() {
            return shop_status;
        }

        public void setShop_status(int shop_status) {
            this.shop_status = shop_status;
        }

        public int getRider_status() {
            return rider_status;
        }

        public void setRider_status(int rider_status) {
            this.rider_status = rider_status;
        }

        public StatusBean getStatus() {
            return status;
        }

        public void setStatus(StatusBean status) {
            this.status = status;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public static class StatusBean {

            private String btn;
            private int status;
            private String btn_txt;

            public String getBtn() {
                return btn;
            }

            public void setBtn(String btn) {
                this.btn = btn;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getBtn_txt() {
                return btn_txt;
            }

            public void setBtn_txt(String btn_txt) {
                this.btn_txt = btn_txt;
            }
        }

        public static class ShopBean {
            private String shop_name;
            private String shop_logo;

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getShop_logo() {
                return shop_logo;
            }

            public void setShop_logo(String shop_logo) {
                this.shop_logo = shop_logo;
            }
        }
    }
}
