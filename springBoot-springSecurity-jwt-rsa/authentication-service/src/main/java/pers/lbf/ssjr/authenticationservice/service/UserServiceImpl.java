package pers.lbf.ssjr.authenticationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.lbf.ssjr.authenticationservice.dao.IPermissonDao;
import pers.lbf.ssjr.authenticationservice.dao.IRoleDao;
import pers.lbf.ssjr.authenticationservice.dao.IUserDao;
import pers.lbf.ssjr.authenticationservice.pojo.to.PermissionDO;
import pers.lbf.ssjr.authenticationservice.pojo.to.RoleDO;
import pers.lbf.ssjr.authenticationservice.pojo.to.UserDO;

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
        return user;
    }
}
