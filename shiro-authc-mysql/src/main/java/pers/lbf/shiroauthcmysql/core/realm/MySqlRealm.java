package pers.lbf.shiroauthcmysql.core.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义Realm对象
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/4 11:00
 */
public class MySqlRealm extends AuthorizingRealm {

    /**
     * 授权
     *
     * @param principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @throws AuthenticationException
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-10-04 11:01:50
     * @version 1.0
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @throws AuthenticationException
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-10-04 11:01:50
     * @version 1.0
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 从token中获取用户名
        String principal = (String) authenticationToken.getPrincipal();

        //2. 根据用户名查询数据库并封装成authenticationinfo对象返回(模拟)
        if (principal == "xiangbei") {
            AuthenticationInfo authInfo = new SimpleAuthenticationInfo("xiangbei", "123", this.getName());
            return authInfo;
        }

        return null;
    }
}
