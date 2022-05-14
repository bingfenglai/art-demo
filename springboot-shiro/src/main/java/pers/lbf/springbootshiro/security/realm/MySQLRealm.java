package pers.lbf.springbootshiro.security.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.springbootshiro.entity.Resource;
import pers.lbf.springbootshiro.entity.Role;
import pers.lbf.springbootshiro.entity.User;
import pers.lbf.springbootshiro.server.resource.service.IResourceService;
import pers.lbf.springbootshiro.server.role.service.IRoleService;
import pers.lbf.springbootshiro.server.user.service.IUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Realm，使用mysql数据源
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 9:09
 */
public class MySQLRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IResourceService resourceService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();

        List<Role> roleList = roleService.findByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : roleList) {
            authorizationInfo.addRole(role.getRoleName());
        }
        List<Long> roleIdList = new ArrayList<>();
        for (Role role : roleList) {
            roleIdList.add(role.getRoleId());
        }

        List<Resource> resourceList = resourceService.findByRoleIds(roleIdList);
        for (Resource resource : resourceList) {
            authorizationInfo.addStringPermission(resource.getResourcePermissionTag());
        }

        return authorizationInfo;
    }


    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token == null) {
            return null;
        }

        String principal = (String) token.getPrincipal();
        User user = userService.findByUsername(principal);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return simpleAuthenticationInfo;
    }
}
