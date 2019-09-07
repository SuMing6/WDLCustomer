package com.example.customer.bean;

import java.util.List;

public class HomeGoodsCarBean {

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

        private int money;
        private int number;
        private List<ListBean> list;

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            private String good_title;
            private String spec_name;
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
