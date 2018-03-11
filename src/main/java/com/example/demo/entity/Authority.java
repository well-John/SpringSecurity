package com.example.demo.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/11.
 */
public class Authority {
    private String url;
    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Authority(String url, String method) {
        this.url = url;
        this.method = method;
    }
}
