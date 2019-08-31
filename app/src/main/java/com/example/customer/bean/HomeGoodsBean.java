package com.example.customer.bean;

import java.util.List;

public class HomeGoodsBean {
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

        private InfoBean info;
        private int cate_id;
        private List<CateBean> cate;
        private List<CouponBean> coupon;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public int getCate_id() {
            return cate_id;
        }

        public void setCate_id(int cate_id) {
            this.cate_id = cate_id;
        }

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
        }

        public List<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public static class InfoBean {

            private int shop_type;
            private String shop_name;
            private String shop_logo;
            private int score;
            private String match_give;

            public int getShop_type() {
                return shop_type;
            }

            public void setShop_type(int shop_type) {
                this.shop_type = shop_type;
            }

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

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getMatch_give() {
                return match_give;
            }

            public void setMatch_give(String match_give) {
                this.match_give = match_give;
            }
        }

        public static class CateBean {
            /**
             * cate_id : 1
             * cate_name : 专场
             */

            private int cate_id;
            private String cate_name;
            private boolean isClicks ;
            public boolean getisClicks() {
                return isClicks;
            }

            public void setisClicks(boolean isClicks) {
                this.isClicks = isClicks;
            }
            public int getCate_id() {
                return cate_id;
            }

            public void setCate_id(int cate_id) {
                this.cate_id = cate_id;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }
        }

        public static class CouponBean {
            private int type;
            private String title;
            private String full;
            private String reduce;
            private int status;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFull() {
                return full;
            }

            public void setFull(String full) {
                this.full = full;
            }

            public String getReduce() {
                return reduce;
            }

            public void setReduce(String reduce) {
                this.reduce = reduce;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
