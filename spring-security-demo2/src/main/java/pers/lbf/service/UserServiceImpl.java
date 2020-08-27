package pers.lbf.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.lbf.dao.UserDaoImpl;
import pers.lbf.pojo.UserDO;

import java.util.Collection;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/8/23 9:48
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDaoImpl userDaoImpl;

    /**
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-08-23 09:49:40
     * @param username username
     * @return UserDetails 这是spring security内部的用户对象
     * @throws UsernameNotFoundException usernameNotFound
     * @version 1.0
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 检验参数
        if (username == null||username.trim().length() == 0) {
            return null;
        }

        //
        QueryWrapper<UserDO> qw = Wrappers.query();

        qw.eq("username",username);
        UserDO user = userDaoImpl.selectOne(qw);
        System.out.println(user.toString());

        //spring security内部认为return null就是认证失败

        return user;
    }
}
