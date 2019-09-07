package com.example.customer.bean;

import java.util.List;

public class HomepageOnclickEightBean {

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
        private List<AdvBean> adv;
        private List<ListBean> list;

        public List<AdvBean> getAdv() {
            return adv;
        }

        public void setAdv(List<AdvBean> adv) {
            this.adv = adv;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AdvBean {

            private String pic;
            private String title;
            private String keywords;
            private int status;
            private String url;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ListBean {

            private int id;
            private String shop_name;
            private String shop_logo;
            private int volume;
            private int score;
            private String rise_give;
            private String match_give;
            private int service_time;
            private String lon;
            private String lat;
            private DistanceBean distance;
            private List<?> coupon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getVolume() {
                return volume;
            }

            public void setVolume(int volume) {
                this.volume = volume;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

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

            public int getService_time() {
                return service_time;
            }

            public void setService_time(int service_time) {
                this.service_time = service_time;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public DistanceBean getDistance() {
                return distance;
            }

            public void setDistance(DistanceBean distance) {
                this.distance = distance;
            }

            public List<?> getCoupon() {
                return coupon;
            }

            public void setCoupon(List<?> coupon) {
                this.coupon = coupon;
            }

            public static class DistanceBean {

                private int status;
                private String distance;

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getDistance() {
                    return distance;
                }

                public void setDistance(String distance) {
                    this.distance = distance;
                }
            }
        }
    }
}
