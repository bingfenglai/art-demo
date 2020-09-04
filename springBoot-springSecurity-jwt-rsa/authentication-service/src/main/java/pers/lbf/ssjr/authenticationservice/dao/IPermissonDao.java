package pers.lbf.ssjr.authenticationservice.dao;

import org.apache.ibatis.annotations.Select;
import pers.lbf.ssjr.authenticationservice.pojo.to.PermissionDO;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/1 21:30
 */
public interface IPermissonDao {

    @Select("select * from sys_permission sp where sp.id in (select pid from sys_role_permission where rid=#{roleId})")
    List<PermissionDO> findByRoleId(Integer roleId);
}
