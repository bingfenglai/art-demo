package pers.lbf.shirodemo.core.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
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
        return null;
    }
}
