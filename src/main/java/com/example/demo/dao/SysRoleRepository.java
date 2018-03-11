package com.example.demo.dao;

import com.example.demo.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/3/11.
 */
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {

    @Query(value = "select r.* from sys_user u left join sys_user_roles sur " +
            "on u.id = sur.sys_user_id left join sys_role r " +
            "on r.id = sur.roles_id  where u.id =:userId", nativeQuery = true)
    public List<SysRole> findRolesByUserId(@Param("userId") Long userid);
}
