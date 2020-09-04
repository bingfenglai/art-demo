package pers.lbf.ssjr.authenticationservice.dao;

import org.apache.ibatis.annotations.Select;
import pers.lbf.ssjr.authenticationservice.pojo.to.UserDO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/8/28 22:17
 */
public interface IUserDao {

    @Select("select * from sys_user u where u.username=#{name}")
    UserDO findByName(String name);
}
