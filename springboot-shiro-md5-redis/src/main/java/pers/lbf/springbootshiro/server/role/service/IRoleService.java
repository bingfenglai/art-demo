package pers.lbf.springbootshiro.server.role.service;

import pers.lbf.springbootshiro.entity.Role;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 15:24
 */
public interface IRoleService {
    List<Role> findByUsername(String username);
}
