package com.example.demo.Servcie;

import com.example.demo.dao.SysPermissionRepository;
import com.example.demo.dao.SysUserRepository;
import com.example.demo.entity.MyGrantedAuthority;
import com.example.demo.entity.SysPermission;
import com.example.demo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/3/8 14:46
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    SysUserRepository userRepository;

    @Autowired
    SysPermissionRepository permissionRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = userRepository.findByUsername(s);
        if (sysUser != null) {
            List<SysPermission> permissions = permissionRepository.findByAdminUserId(sysUser.getId());
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (SysPermission permission :
                    permissions) {
                if (permission != null && permission.getName() != null && permission.getUrl() != null) {
                    MyGrantedAuthority myGrantedAuthority = new MyGrantedAuthority(permission.getUrl(), permission.getMethod(),permission.getName());
                    grantedAuthorities.add(myGrantedAuthority);
                }
            }
            return new User(sysUser.getUsername(),sysUser.getPassword(),grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}
