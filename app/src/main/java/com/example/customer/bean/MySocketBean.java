package com.example.customer.bean;

import java.util.List;

public class MySocketBean {

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
        private int chat_id;
        private int user_id;
        private int type;
        private String content;
        private int is_read;
        private int user_a_del;
        private int user_b_del;
        private String add_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getChat_id() {
            return chat_id;
        }

        public void setChat_id(int chat_id) {
            this.chat_id = chat_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }

        public int getUser_a_del() {
            return user_a_del;
        }

        public void setUser_a_del(int user_a_del) {
            this.user_a_del = user_a_del;
        }

        public int getUser_b_del() {
            return user_b_del;
        }

        public void setUser_b_del(int user_b_del) {
            this.user_b_del = user_b_del;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
