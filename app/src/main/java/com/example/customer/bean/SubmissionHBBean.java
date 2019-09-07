package com.example.customer.bean;

import java.util.List;

public class SubmissionHBBean {

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
        private String reduce;
        private String time_end;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReduce() {
            return reduce;
        }

        public void setReduce(String reduce) {
            this.reduce = reduce;
        }

        public String getTime_end() {
            return time_end;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }
    }
}
