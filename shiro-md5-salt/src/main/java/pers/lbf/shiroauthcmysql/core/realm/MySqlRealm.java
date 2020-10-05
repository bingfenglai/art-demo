package pers.lbf.shiroauthcmysql.core.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**自定义Realm对象
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/4 11:00
 */
public class MySqlRealm extends AuthorizingRealm {

    public MySqlRealm() {
        //设置凭证匹配器，修改为hash凭证匹配器
        HashedCredentialsMatcher myCredentialsMatcher = new HashedCredentialsMatcher();
        //设置算法
        myCredentialsMatcher.setHashAlgorithmName("md5");
        //散列次数
        myCredentialsMatcher.setHashIterations(1024);
        this.setCredentialsMatcher(myCredentialsMatcher);
    }

    /**授权方法
     * 对于授权方法，每次判断主体是否具备对应权限时都会调用
     * 因此，这里应当做缓存
     * 缓存会在后面与springboot整合时讲
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-10-04 11:01:50
     * @param principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @throws AuthenticationException
     * @version 1.0
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1. 获取当前主体的主身份信息，即用户名
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //2. 根据主身份信息查询数据库，获取主体具备的权限（模拟）
        SimpleAuthorizationInfo authenticationInfo = null;
        if ("xiangbei".equals(primaryPrincipal)){
            authenticationInfo = new SimpleAuthorizationInfo();
            //authenticationInfo.addRole("admin");

            //具备user的所有权限
            authenticationInfo.addStringPermission("user:*");

            //具备产品的创建权限
            //authenticationInfo.addStringPermission("product:create");
        }
        return authenticationInfo;
    }

    /**认证
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-10-04 11:01:50
     * @param authenticationToken
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @throws AuthenticationException
     * @version 1.0
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 从token中获取用户名
        String principal = (String) authenticationToken.getPrincipal();

        //2. 根据用户名查询数据库并封装成authenticationinfo对象返回(模拟)
        if (principal == "xiangbei") {
            //四个参数分别是数据库中的账号、加密后的密码、盐值、realm名字
            AuthenticationInfo authInfo = new SimpleAuthenticationInfo("xiangbei",
                    "ff595c47b51b4cf70fddce090f68879e",
                    ByteSource.Util.bytes("ee575f62-0dda-44f2-b75e-4efef795018f"),
                    this.getName());
            return authInfo;
        }

        return null;
    }
}
