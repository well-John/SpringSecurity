package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by yangyibo on 17/2/15.
 */
public class MyGrantedAuthority implements GrantedAuthority {

    private String url;
    private String method;
    private String authority;


    public String getPermissionUrl() {
        return url;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.url = permissionUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public MyGrantedAuthority(String url, String method,String authority) {
        this.url = url;
        this.method = method;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}