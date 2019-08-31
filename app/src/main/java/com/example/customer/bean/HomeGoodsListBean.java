package com.example.customer.bean;

import java.util.List;

public class HomeGoodsListBean {

    /**
     * code : 0
     * msg : ok
     * data : [{"id":1,"img":"http://192.168.8.137:8010//uploads/shop/1/images/20190827\\\\707ecc3fc39cb33319aa69f831254091.jpg","volume":0,"title":"葱香烤鱼","spec":[{"id":1,"spec_norms":"5个","spec_money":"10.00","spec_stock":20},{"id":2,"spec_norms":"200g","spec_money":"15.00","spec_stock":80}]},{"id":2,"img":"http://192.168.8.137:8010//uploads/shop/1/images/20190827\\\\eb935ca537a4a2007f5b39cb3ac63b15.jpg","volume":0,"title":"大份大盘鸡秘制","spec":[{"id":3,"spec_norms":"500g","spec_money":"68.00","spec_stock":999}]}]
     * token :
     */

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
        /**
         * id : 1
         * img : http://192.168.8.137:8010//uploads/shop/1/images/20190827\\707ecc3fc39cb33319aa69f831254091.jpg
         * volume : 0
         * title : 葱香烤鱼
         * spec : [{"id":1,"spec_norms":"5个","spec_money":"10.00","spec_stock":20},{"id":2,"spec_norms":"200g","spec_money":"15.00","spec_stock":80}]
         */

        private int id;
        private String img;
        private int volume;
        private String title;
        private List<SpecBean> spec;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<SpecBean> getSpec() {
            return spec;
        }

        public void setSpec(List<SpecBean> spec) {
            this.spec = spec;
        }

        public static class SpecBean {
            /**
             * id : 1
             * spec_norms : 5个
             * spec_money : 10.00
             * spec_stock : 20
             */

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
