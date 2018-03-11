package com.example.demo.Servcie;

import com.example.demo.dao.SysPermissionRepository;
import com.example.demo.dao.SysRoleRepository;
import com.example.demo.dao.SysUserRepository;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.management.relation.Role;
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

    @Autowired
    SysRoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = userRepository.findByUsername(s);
        if (sysUser != null) {
            List<SysRole> roles = roleRepository.findRolesByUserId(sysUser.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (SysRole role : roles) {
                List<Authority> authorities = new ArrayList<>();
                List<SysPermission> permissions = permissionRepository.findSysPermissionsByRoleId(role.getId());
                permissions.forEach(p -> {
                    if (p != null && p.getUrl() != null && p.getMethod() !=null){
                        Authority authority = new Authority(p.getUrl(),p.getMethod());
                        authorities.add(authority);
                    }
                });
                MyGrantedAuthority myGrantedAuthority = new MyGrantedAuthority(authorities,role.getName());
                grantedAuthorities.add(myGrantedAuthority);
            }
            return new User(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}
