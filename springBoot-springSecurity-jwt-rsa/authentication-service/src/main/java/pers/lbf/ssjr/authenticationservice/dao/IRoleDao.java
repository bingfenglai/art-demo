package pers.lbf.ssjr.authenticationservice.dao;

import org.apache.ibatis.annotations.Select;
import pers.lbf.ssjr.authenticationservice.pojo.to.RoleDO;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/1 20:53
 */
public interface IRoleDao {

    @Select("select * from sys_role sr where sr.id in (select rid from sys_user_role where uid=#{userId})")
    List<RoleDO> findByUserId(Integer userId);
}
