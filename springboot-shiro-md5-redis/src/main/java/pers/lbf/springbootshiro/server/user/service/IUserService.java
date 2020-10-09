package pers.lbf.springbootshiro.server.user.service;

import pers.lbf.springbootshiro.entity.User;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 15:21
 */
public interface IUserService {
    User findByUsername(String principal);
}
