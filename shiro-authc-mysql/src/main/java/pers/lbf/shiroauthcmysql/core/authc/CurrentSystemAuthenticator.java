package pers.lbf.shiroauthcmysql.core.authc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import pers.lbf.shiroauthcmysql.core.realm.MySqlRealm;

/**
 * 认证管理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/4 11:11
 */
public class CurrentSystemAuthenticator {
    private DefaultSecurityManager securityManager;

    public CurrentSystemAuthenticator() {
        //创建安全管理器
        securityManager = new DefaultSecurityManager();

        //设置自定义realm
        this.securityManager.setRealm(new MySqlRealm());

        //将安全管理器设置到安全工具类中
        SecurityUtils.setSecurityManager(securityManager);

    }

    public void authenticate(String username, String password) {
        //获取当前登录主题
        Subject subject = SecurityUtils.getSubject();

        //生成toeken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //进行认证
        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            System.out.println("用户名或密码不正确");
        }


        //打印认证状态
        if (subject.isAuthenticated()) {
            System.out.println(token.getPrincipal() + " 认证通过！");
        } else {
            System.out.println(token.getPrincipal() + " 认证未通过！");
        }


    }
}
