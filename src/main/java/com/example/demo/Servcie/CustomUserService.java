package com.example.demo.Servcie;

import com.example.demo.dao.SysPermissionRepository;
import com.example.demo.dao.SysUserRepository;
import com.example.demo.entity.SysPermission;
import com.example.demo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

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
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (SysPermission permission :
                    permissions) {
                if (permission != null && permission.getName() != null){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return  sysUser;
        }else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}
