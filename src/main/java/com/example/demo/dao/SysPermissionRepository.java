package com.example.demo.dao;

import com.example.demo.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/3/9 14:26
 */
public interface SysPermissionRepository extends JpaRepository<SysPermission,Long> {

    List<SysPermission> findAll();

    @Query(value = "SELECT p.* from sys_user u left JOIN sys_user_roles sru ON u.id = sru.sys_user_id" +
            " left JOIN  sys_role r on sru.roles_id = r.id" +
            " left JOIN  sys_role_permissions srp on srp.sys_role_id = r.id" +
            " left JOIN  sys_permission p on srp.permissions_id = p.id where u.id = :userId",nativeQuery = true)
    List<SysPermission> findByAdminUserId(@Param("userId") Long userId);


    @Query(value = "select p.* from  sys_role r left join sys_role_permissions srp on r.id = srp.sys_role_id   " +
            "left join sys_permission p on p.id = srp.permissions_id " +
            "where r.id =:roleId",nativeQuery = true)
    List<SysPermission> findSysPermissionsByRoleId(@Param("roleId") Long roleId);
}
