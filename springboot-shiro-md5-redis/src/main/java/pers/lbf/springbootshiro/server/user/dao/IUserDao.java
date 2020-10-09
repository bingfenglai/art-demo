package pers.lbf.springbootshiro.server.user.dao;

import org.apache.ibatis.annotations.Select;
import pers.lbf.springbootshiro.entity.User;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 15:23
 */
public interface IUserDao {

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);
}
