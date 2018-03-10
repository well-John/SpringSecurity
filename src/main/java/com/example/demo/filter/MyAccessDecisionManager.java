package com.example.demo.filter;

import com.example.demo.entity.MyGrantedAuthority;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/3/9 15:16
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String method;
        String url;
        AntPathRequestMatcher matcher;
        Object p =  authentication.getPrincipal();
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (ga instanceof MyGrantedAuthority) {
                MyGrantedAuthority myGrantedAuthority = (MyGrantedAuthority) ga;
                String au = myGrantedAuthority.getAuthority();
                url = myGrantedAuthority.getPermissionUrl();
                method = myGrantedAuthority.getMethod();
                matcher = new AntPathRequestMatcher(url);
                if (matcher.matches(request)) {
                    if (request.getMethod().equals(method) || "ALL".equals(method)) {
                        return;
                    }
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
