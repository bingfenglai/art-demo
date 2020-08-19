package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                many = @Many(select = "com.itheima.dao.RoleDao.findByUid"))
    })
    public SysUser findByName(String username);

    @Insert("insert into sys_user (username, password) values (#{username}, #{password})")
    public void save(SysUser user);

    @Select("select * from sys_user")
    public List<SysUser> findAll();

    @Select("SELECT r.id FROM sys_role r, sys_user_role ur " +
            "WHERE ur.rid=r.id AND ur.uid=#{uid}")
    public List<Integer> findRolesByUid(Integer id);

    @Delete("delete from sys_user_role where uid=#{userId}")
    public void removeRoles(Integer userId);

    @Insert("insert into sys_user_role values (#{uid}, #{rid})")
    public void addRoles(@Param("uid") Integer userId, @Param("rid") Integer rid);
}
