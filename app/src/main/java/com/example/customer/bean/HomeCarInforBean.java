package com.example.customer.bean;

import java.util.List;

public class HomeCarInforBean {

    /**
     * code : 0
     * msg : ok
     * data : {"money":123,"number":5,"pingtaishoudan":"1","shangjiashoudan":"10.00","manjianyouhui":50,"shangjiahongbao":0,"shangjiayouhuiquan":0,"shijizhifu":64,"shop_info":{"rise_give":"10.00","match_give":"1.00","pack":"1.00","range":"105.451543,11.998503|105.468862,11.998503|105.468862,11.981486|105.451543,11.981486","delivery":0},"good_list":[{"good_id":1,"spec_id":1,"good_title":"葱香烤鱼","spec_name":"5个","good_money":"10.00","good_num":1},{"good_id":1,"spec_id":2,"good_title":"葱香烤鱼","spec_name":"200g","good_money":"15.00","good_num":3},{"good_id":2,"spec_id":3,"good_title":"大份大盘鸡秘制","spec_name":"500g","good_money":"68.00","good_num":1}]}
     * token :
     */

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
        /**
         * money : 123
         * number : 5
         * pingtaishoudan : 1
         * shangjiashoudan : 10.00
         * manjianyouhui : 50
         * shangjiahongbao : 0
         * shangjiayouhuiquan : 0
         * shijizhifu : 64
         * shop_info : {"rise_give":"10.00","match_give":"1.00","pack":"1.00","range":"105.451543,11.998503|105.468862,11.998503|105.468862,11.981486|105.451543,11.981486","delivery":0}
         * good_list : [{"good_id":1,"spec_id":1,"good_title":"葱香烤鱼","spec_name":"5个","good_money":"10.00","good_num":1},{"good_id":1,"spec_id":2,"good_title":"葱香烤鱼","spec_name":"200g","good_money":"15.00","good_num":3},{"good_id":2,"spec_id":3,"good_title":"大份大盘鸡秘制","spec_name":"500g","good_money":"68.00","good_num":1}]
         */

        private int money;
        private int number;
        private String pingtaishoudan;
        private String shangjiashoudan;
        private int manjianyouhui;
        private int shangjiahongbao;
        private int shangjiayouhuiquan;
        private int shijizhifu;
        private ShopInfoBean shop_info;
        private List<GoodListBean> good_list;

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

        public String getPingtaishoudan() {
            return pingtaishoudan;
        }

        public void setPingtaishoudan(String pingtaishoudan) {
            this.pingtaishoudan = pingtaishoudan;
        }

        public String getShangjiashoudan() {
            return shangjiashoudan;
        }

        public void setShangjiashoudan(String shangjiashoudan) {
            this.shangjiashoudan = shangjiashoudan;
        }

        public int getManjianyouhui() {
            return manjianyouhui;
        }

        public void setManjianyouhui(int manjianyouhui) {
            this.manjianyouhui = manjianyouhui;
        }

        public int getShangjiahongbao() {
            return shangjiahongbao;
        }

        public void setShangjiahongbao(int shangjiahongbao) {
            this.shangjiahongbao = shangjiahongbao;
        }

        public int getShangjiayouhuiquan() {
            return shangjiayouhuiquan;
        }

        public void setShangjiayouhuiquan(int shangjiayouhuiquan) {
            this.shangjiayouhuiquan = shangjiayouhuiquan;
        }

        public int getShijizhifu() {
            return shijizhifu;
        }

        public void setShijizhifu(int shijizhifu) {
            this.shijizhifu = shijizhifu;
        }

        public ShopInfoBean getShop_info() {
            return shop_info;
        }

        public void setShop_info(ShopInfoBean shop_info) {
            this.shop_info = shop_info;
        }

        public List<GoodListBean> getGood_list() {
            return good_list;
        }

        public void setGood_list(List<GoodListBean> good_list) {
            this.good_list = good_list;
        }

        public static class ShopInfoBean {
            /**
             * rise_give : 10.00
             * match_give : 1.00
             * pack : 1.00
             * range : 105.451543,11.998503|105.468862,11.998503|105.468862,11.981486|105.451543,11.981486
             * delivery : 0
             */

            private String rise_give;
            private String match_give;
            private String pack;
            private String range;
            private int delivery;

            public String getRise_give() {
                return rise_give;
            }

            public void setRise_give(String rise_give) {
                this.rise_give = rise_give;
            }

            public String getMatch_give() {
                return match_give;
            }

            public void setMatch_give(String match_give) {
                this.match_give = match_give;
            }

            public String getPack() {
                return pack;
            }

            public void setPack(String pack) {
                this.pack = pack;
            }

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public int getDelivery() {
                return delivery;
            }

            public void setDelivery(int delivery) {
                this.delivery = delivery;
            }
        }

        public static class GoodListBean {
            /**
             * good_id : 1
             * spec_id : 1
             * good_title : 葱香烤鱼
             * spec_name : 5个
             * good_money : 10.00
             * good_num : 1
             */

            private int good_id;
            private int spec_id;
            private String good_title;
            private String spec_name;
            private String good_money;
            private int good_num;

            public int getGood_id() {
                return good_id;
            }

            public void setGood_id(int good_id) {
                this.good_id = good_id;
            }

            public int getSpec_id() {
                return spec_id;
            }

            public void setSpec_id(int spec_id) {
                this.spec_id = spec_id;
            }

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
