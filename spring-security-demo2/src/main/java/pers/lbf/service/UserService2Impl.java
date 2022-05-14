package pers.lbf.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.lbf.dao.UserDao2Impl;
import pers.lbf.pojo.MyUserDO;
import pers.lbf.pojo.RoleDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/8/27 15:20
 */
@Service
public class UserService2Impl implements UserDetailsService {

    @Autowired
    private UserDao2Impl userDaoImpl;


    /**
     * 使用spring security已经实现的user对象进行认证
     *
     * @param username
     * @return org.springframework.security.core.userdetails.UserDetails
     * @throws UsernameNotFoundException
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-08-27 15:24:28
     * @version 1.0
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 检验参数
        if (username == null || username.trim().length() == 0) {
            return null;
        }

        //构造条件（我使用的是MybatisPlus）
        QueryWrapper<MyUserDO> qw = Wrappers.query();
        qw.eq("username", username);

        //从数据库中查出自己的user对象
        MyUserDO myuser = userDaoImpl.selectOne(qw);

        //模拟获取用户具备的角色信息
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();

        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        //封装成spring security的user对象
        //注意：这里的密码如果不是加密村存储的要加上"{noop}"
        User user = new User(myuser.getUsername(), myuser.getPassword(), simpleGrantedAuthorityList);
        return user;
    }
}
