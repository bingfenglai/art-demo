package pers.lbf.springbootshiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.lbf.springbootshiro.security.realm.MySQLRealm;

import java.util.HashMap;

/**shiro配置类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 9:11
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilter拦截器
     * @return ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //配置不拦截路径和拦截路径，顺序不能反
        HashMap<String, String> map = new HashMap<>(5);

        map.put("/authc/**","anon");
        map.put("/login.html","anon");
        map.put("/js/**","anon");
        map.put("/css/**","anon");

        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //覆盖默认的登录url
        shiroFilterFactoryBean.setLoginUrl("/authc/unauthc");
        return shiroFilterFactoryBean;
    }

    @Bean
    public Realm getRealm(){
        //设置凭证匹配器，修改为hash凭证匹配器
        HashedCredentialsMatcher myCredentialsMatcher = new HashedCredentialsMatcher();
        //设置算法
        myCredentialsMatcher.setHashAlgorithmName("md5");
        //散列次数
        myCredentialsMatcher.setHashIterations(1024);
        MySQLRealm realm = new MySQLRealm();
        realm.setCredentialsMatcher(myCredentialsMatcher);
        return realm;
    }

    /**
     * 创建shiro web应用下的安全管理器
     * @return DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getSecurityManager(Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

}
