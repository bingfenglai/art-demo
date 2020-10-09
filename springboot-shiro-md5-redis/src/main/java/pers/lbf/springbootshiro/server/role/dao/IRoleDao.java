package pers.lbf.springbootshiro.server.role.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.lbf.springbootshiro.entity.Role;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 15:26
 */
public interface IRoleDao {

    @Select("select role_id from user_role  where username=#{username}")
    List<Long> findByUsername(String username);

    @Select("select * from role where role_id in (1)")
    List<Role> findByRoleIds(@Param("roleIdList") List<Long> roleIdList);

    @Select("select * from role where role_id in (" +
            " select role_id from user_role where username=#{username})")
    List<Role> findAllRoleByUsername(String username);

}
