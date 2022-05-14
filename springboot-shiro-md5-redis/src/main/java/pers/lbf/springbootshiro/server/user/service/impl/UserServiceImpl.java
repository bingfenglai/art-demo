package pers.lbf.springbootshiro.server.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lbf.springbootshiro.entity.User;
import pers.lbf.springbootshiro.server.user.dao.IUserDao;
import pers.lbf.springbootshiro.server.user.service.IUserService;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 15:21
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public User findByUsername(String principal) {
        if (principal == null || "".equals(principal)) {
            return null;
        }

        return userDao.findByUsername(principal);
    }
}
