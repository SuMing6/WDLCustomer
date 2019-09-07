package com.example.customer.bean;

import java.util.List;

public class MyScDataBean {

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

        private int mid;
        private ShopBean shop;
        private DistanceBean distance;

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public DistanceBean getDistance() {
            return distance;
        }

        public void setDistance(DistanceBean distance) {
            this.distance = distance;
        }

        public static class ShopBean {

            private String shop_name;
            private String shop_logo;
            private int volume;
            private String lon;
            private String lat;

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
        }

        public static class DistanceBean {

            private int status;
            private String distance;
            private double rice;

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

            public double getRice() {
                return rice;
            }

            public void setRice(double rice) {
                this.rice = rice;
            }
        }
    }
}
