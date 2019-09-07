package com.example.customer.data;

import java.util.List;

public class OrderInfoBean {

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

        private OrderBean order;
        private DetailBean detail;
        private List<GoodBean> good;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public List<GoodBean> getGood() {
            return good;
        }

        public void setGood(List<GoodBean> good) {
            this.good = good;
        }

        public static class OrderBean {
            private int shop_id;
            private String order;
            private int user_status;
            private int shop_status;
            private int rider_status;
            private String pack;
            private String delivery;
            private String bonus;
            private String shop_bonus;
            private String red;
            private String shop_shoudan;
            private String money_paid;
            private String order_amount;
            private String first_bonus;
            private int arrival;
            private String expect;
            private String expect_time;
            private String user_time;
            private int pay_id;

            public int getShop_id() {
                return shop_id;
            }

            public void setShop_id(int shop_id) {
                this.shop_id = shop_id;
            }

            public String getOrder() {
                return order;
            }

            public void setOrder(String order) {
                this.order = order;
            }

            public int getUser_status() {
                return user_status;
            }

            public void setUser_status(int user_status) {
                this.user_status = user_status;
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

            public String getPack() {
                return pack;
            }

            public void setPack(String pack) {
                this.pack = pack;
            }

            public String getDelivery() {
                return delivery;
            }

            public void setDelivery(String delivery) {
                this.delivery = delivery;
            }

            public String getBonus() {
                return bonus;
            }

            public void setBonus(String bonus) {
                this.bonus = bonus;
            }

            public String getShop_bonus() {
                return shop_bonus;
            }

            public void setShop_bonus(String shop_bonus) {
                this.shop_bonus = shop_bonus;
            }

            public String getRed() {
                return red;
            }

            public void setRed(String red) {
                this.red = red;
            }

            public String getShop_shoudan() {
                return shop_shoudan;
            }

            public void setShop_shoudan(String shop_shoudan) {
                this.shop_shoudan = shop_shoudan;
            }

            public String getMoney_paid() {
                return money_paid;
            }

            public void setMoney_paid(String money_paid) {
                this.money_paid = money_paid;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getFirst_bonus() {
                return first_bonus;
            }

            public void setFirst_bonus(String first_bonus) {
                this.first_bonus = first_bonus;
            }

            public int getArrival() {
                return arrival;
            }

            public void setArrival(int arrival) {
                this.arrival = arrival;
            }

            public String getExpect() {
                return expect;
            }

            public void setExpect(String expect) {
                this.expect = expect;
            }

            public String getExpect_time() {
                return expect_time;
            }

            public void setExpect_time(String expect_time) {
                this.expect_time = expect_time;
            }

            public String getUser_time() {
                return user_time;
            }

            public void setUser_time(String user_time) {
                this.user_time = user_time;
            }

            public int getPay_id() {
                return pay_id;
            }

            public void setPay_id(int pay_id) {
                this.pay_id = pay_id;
            }
        }

        public static class DetailBean {

            private String shop_name;
            private String shop_mobile;
            private String deliver_name;
            private String deliver_mobile;

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getShop_mobile() {
                return shop_mobile;
            }

            public void setShop_mobile(String shop_mobile) {
                this.shop_mobile = shop_mobile;
            }

            public String getDeliver_name() {
                return deliver_name;
            }

            public void setDeliver_name(String deliver_name) {
                this.deliver_name = deliver_name;
            }

            public String getDeliver_mobile() {
                return deliver_mobile;
            }

            public void setDeliver_mobile(String deliver_mobile) {
                this.deliver_mobile = deliver_mobile;
            }
        }

        public static class GoodBean {

            private String good_title;
            private String spec_name;
            private String good_img;
            private String good_money;
            private int good_num;

            public String getGood_title() {
                return good_title;
            }

            public void setGood_title(String good_title) {
                this.good_title = good_title;
            }

            public String getSpec_name() {
                return spec_name;
            }

            public void setSpec_name(String spec_name) {
                this.spec_name = spec_name;
            }

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
