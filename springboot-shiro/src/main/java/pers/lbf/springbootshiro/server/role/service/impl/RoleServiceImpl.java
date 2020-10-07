package pers.lbf.springbootshiro.server.role.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lbf.springbootshiro.entity.Role;
import pers.lbf.springbootshiro.server.role.dao.IRoleDao;
import pers.lbf.springbootshiro.server.role.service.IRoleService;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 15:25
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findByUsername(String username) {
        if (username == null){
            return null;
        }

        List<Role> roleList = roleDao.findAllRoleByUsername(username);

        return roleList;
    }
}
