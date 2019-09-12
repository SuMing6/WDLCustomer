package com.example.customer.bean;

import java.util.List;

public class HomePageGoodSousuoBean {

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
        private int shop_cate_id;
        private String img;
        private String title;
        private int is_del;
        private int top;
        private int frame;
        private int volume;
        private String add_time;
        private int up_time;
        private List<SpecBean> spec;

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

        public int getShop_cate_id() {
            return shop_cate_id;
        }

        public void setShop_cate_id(int shop_cate_id) {
            this.shop_cate_id = shop_cate_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getFrame() {
            return frame;
        }

        public void setFrame(int frame) {
            this.frame = frame;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getUp_time() {
            return up_time;
        }

        public void setUp_time(int up_time) {
            this.up_time = up_time;
        }

        public List<SpecBean> getSpec() {
            return spec;
        }

        public void setSpec(List<SpecBean> spec) {
            this.spec = spec;
        }

        public static class SpecBean {

            private int id;
            private String spec_norms;
            private String spec_money;
            private int spec_stock;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSpec_norms() {
                return spec_norms;
            }

            public void setSpec_norms(String spec_norms) {
                this.spec_norms = spec_norms;
            }

            public String getSpec_money() {
                return spec_money;
            }

            public void setSpec_money(String spec_money) {
                this.spec_money = spec_money;
            }

            public int getSpec_stock() {
                return spec_stock;
            }

            public void setSpec_stock(int spec_stock) {
                this.spec_stock = spec_stock;
            }
        }
    }
}
