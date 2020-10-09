package pers.lbf.springbootshiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.lbf.springbootshiro.shiro.generator.ShiroSessionIdGenerator;
import pers.lbf.springbootshiro.shiro.manager.ShiroSessionManager;
import pers.lbf.springbootshiro.shiro.realm.MySQLRealm;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;

/**shiro配置类
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 9:11
 */
@Configuration
public class ShiroConfig {

    private static final String CACHE_KEY = "shiro:cache:";
    private static final String SESSION_KEY = "shiro:session:";
    private static final int EXPIRE = 18000;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


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
        //覆盖默认的登录url
        shiroFilterFactoryBean.setLoginUrl("/authc/unauthc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

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
        //开启缓存
        realm.setCachingEnabled(true);
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthorizationCachingEnabled(true);
        return realm;
    }

    /**
     * 创建shiro web应用下的安全管理器
     * @return DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getSecurityManager( Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
       securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }



    /**
     * 配置Redis管理器
     * @Attention 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxIdle+maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        redisManager.setJedisPoolConfig(jedisPoolConfig);

        return redisManager;
    }


    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setKeyPrefix(CACHE_KEY);
        // shiro-redis要求放在session里面的实体类必须有个id标识
        //这是组成redis中所存储数据的key的一部分
        redisCacheManager.setPrincipalIdFieldName("username");
        return redisCacheManager;
    }


    /**
     * SessionID生成器
     *
     */
    @Bean
    public ShiroSessionIdGenerator sessionIdGenerator(){
        return new ShiroSessionIdGenerator();
    }

    /**
     * 配置RedisSessionDAO
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        redisSessionDAO.setKeyPrefix(SESSION_KEY);
        redisSessionDAO.setExpire(EXPIRE);
        return redisSessionDAO;
    }

    /**
     * 配置Session管理器
     * @Author Sans
     *
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager shiroSessionManager = new ShiroSessionManager();
        shiroSessionManager.setSessionDAO(redisSessionDAO());
        shiroSessionManager.setSessionIdCookieEnabled(false);
        shiroSessionManager.setSessionIdUrlRewritingEnabled(false);
        return shiroSessionManager;
    }


}
