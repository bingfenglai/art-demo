package pers.lbf.springbootspringsecuriotydemo1.service;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.lbf.springbootspringsecuriotydemo1.dao.IPermissonDao;
import pers.lbf.springbootspringsecuriotydemo1.dao.IRoleDao;
import pers.lbf.springbootspringsecuriotydemo1.dao.IUserDao;
import pers.lbf.springbootspringsecuriotydemo1.pojo.PermissionDO;
import pers.lbf.springbootspringsecuriotydemo1.pojo.RoleDO;
import pers.lbf.springbootspringsecuriotydemo1.pojo.UserDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/8/28 22:16
 */
@Service("userService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IPermissonDao permissonDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            return null;
        }
        System.out.println("用户名" + username);
        UserDO user = userDao.findByName(username);

        List<RoleDO> roleList = roleDao.findByUserId(user.getId());

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (RoleDO roleDO : roleList) {
            List<PermissionDO> permissionListItems = permissonDao.findByRoleId(roleDO.getId());
            for (PermissionDO permissionDO : permissionListItems) {
                list.add(new SimpleGrantedAuthority(permissionDO.getPermissionUrl()));
            }
        }
        user.setAuthorityList(list);
        System.out.println(user);
        return user;
    }
}
