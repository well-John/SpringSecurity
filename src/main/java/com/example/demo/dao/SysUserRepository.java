package com.example.demo.dao;

import com.example.demo.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/3/8 14:44
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    SysUser findByUsername(String username);
}
