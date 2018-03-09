package com.example.demo.filter;

import com.example.demo.dao.SysPermissionRepository;
import com.example.demo.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/3/9 14:20
 */
@Component
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysPermissionRepository permissionRepository;

    private HashMap<String, Collection<ConfigAttribute>> map = null;


    /**
     * 加载资源，初始化资源变量
     */
    public void loadResource() {
        map = new HashMap<>();

        List<SysPermission> permissions = permissionRepository.findAll();
        permissions.stream().forEach(x -> {
            Collection<ConfigAttribute> attributes = new ArrayList<>();
            ConfigAttribute cfg = new SecurityConfig(x.getName());
            attributes.add(cfg);
            map.put(x.getUrl(), attributes);
        });

    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            loadResource();
        }
        javax.servlet.http.HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
