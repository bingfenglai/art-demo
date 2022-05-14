package pers.lbf.ssjr.authenticationservice.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pers.lbf.ssjr.authenticationservice.web.handler.MyAccessDeniedHandler;
import pers.lbf.ssjr.authenticationservice.web.handler.MyAuthenticationEntryPoint;
import pers.lbf.ssjr.authenticationservice.web.security.filter.TokenLoginFilter;
import pers.lbf.ssjr.authenticationservice.web.security.filter.TokenVerifyFilter;

/**
 * spring security配置类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/3 15:41
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private AuthServerRsaKeyProperties properties;

    @Bean
    public BCryptPasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 配置自定义过滤器
     *
     * @param http
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-03 15:53:45
     * @version 1.0
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //禁用跨域保护，取代它的是jwt
        http.csrf().disable();

        //允许匿名访问的方法
        http.authorizeRequests().antMatchers("/login").anonymous();
        //其他需要鉴权
        //.anyRequest().authenticated();

        //添加认证过滤器
        http.addFilter(new TokenLoginFilter(authenticationManager(), properties));

        //添加验证过滤器
        http.addFilter(new TokenVerifyFilter(authenticationManager(), properties));

        //添加自定义异常处理
        http.exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint());
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());

        //禁用session,前后端分离是无状态的
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }


    /**
     * 配置密码加密策略
     *
     * @param authenticationManagerBuilder
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-03 15:50:46
     * @version 1.0
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(myPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        //忽略静态资源
        webSecurity.ignoring().antMatchers("/assents/**", "/login.html");
    }

}
