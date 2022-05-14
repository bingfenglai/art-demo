package pers.lbf.shirodemo.core;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * 认证器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/21 0:50
 */
public class Authenticator {

    private DefaultSecurityManager securityManager;

    public Authenticator() {
        //1. 创建安全管理器
        this.securityManager = new DefaultSecurityManager();

        //2. 给安全管理器设置问题域
        //因为权限信息从ini文件中读取，所以是IniRealm
        this.securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        //3. 注入安全管理器，并使用SecurityUtils全局安全工具类完成认证
        SecurityUtils.setSecurityManager(securityManager);


    }

    /**
     * 认证
     *
     * @param username 用户名
     * @param password 密码
     * @return void
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-23 16:22:11
     * @version 1.0
     */
    public void authenticate(String username, String password) {
        //4. 获取当前主题
        Subject subject = SecurityUtils.getSubject();

        //5.根据登录对象身份凭证信息创建登录令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //6.认证
        //如果认证通过，则不抛出异常，否则抛出AuthenticationExceptixon异常子类
        //正式项目建议直接抛出，统一异常处理
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
        } catch (ConcurrentAccessException e) {
            e.printStackTrace();
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        } catch (ExcessiveAttemptsException e) {
            e.printStackTrace();
        } catch (ExpiredCredentialsException e) {
            e.printStackTrace();
        } catch (LockedAccountException e) {
            e.printStackTrace();
        }

    }


}
