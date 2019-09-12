package com.example.customer.bean;

import java.util.List;

public class HomeSousuoLsBean {

    private int code;
    private String msg;
    private List<?> hot_list;
    private List<ListBean> list;

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

    public List<?> getHot_list() {
        return hot_list;
    }

    public void setHot_list(List<?> hot_list) {
        this.hot_list = hot_list;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {

        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
