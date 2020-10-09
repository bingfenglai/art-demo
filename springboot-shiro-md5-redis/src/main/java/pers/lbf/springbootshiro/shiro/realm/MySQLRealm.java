package pers.lbf.springbootshiro.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.springbootshiro.entity.Resource;
import pers.lbf.springbootshiro.entity.Role;
import pers.lbf.springbootshiro.entity.User;
import pers.lbf.springbootshiro.server.resource.service.IResourceService;
import pers.lbf.springbootshiro.server.role.service.IRoleService;
import pers.lbf.springbootshiro.server.user.service.IUserService;
import pers.lbf.springbootshiro.shiro.authcinfo.MyAuthcInfo;
import pers.lbf.springbootshiro.shiro.salt.CurrentSalt;
import pers.lbf.springbootshiro.utils.ShiroUtils;

import java.util.ArrayList;
import java.util.List;

/**自定义Realm，使用mysql数据源
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
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       User user = (User) principals.getPrimaryPrincipal();
        String username = user.getUsername();
        List<Role> roleList = roleService.findByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : roleList) {
            authorizationInfo.addRole(role.getRoleName());
        }
        List<Long> roleIdList  = new ArrayList<>();
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
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        if(token==null){
            return null;
        }
        String principal = (String) token.getPrincipal();
        User user = userService.findByUsername(principal);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new MyAuthcInfo(
                //由于shiro-redis插件需要从这个属性中获取id作为redis的key
                //所有这里传的是user而不是username
                user,
                //凭证信息
                user.getPassword(),
                //加密盐值
                new CurrentSalt(user.getSalt()),
                getName());

        //清除当前主体旧的会话，相当于你在新电脑上登录系统，把你之前在旧电脑上登录的会话挤下去
        ShiroUtils.deleteCache(user.getUsername(),true);
        return simpleAuthenticationInfo;
    }
}
