package pers.lbf.springbootactiviti.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import javax.vecmath.Tuple2d;
import java.util.Collection;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020-08-18 11:27:31
 */
@Component
public class SecurityUtil {

    @Autowired
    private UserDetailsService userDetailsService;


    public void logInAs(String username) {

        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User " + username + " doesn't exist, please provide a valid user");
        }

        Authentication authentication = new Authentication() {

            @Override
            public String getName() {

                return user.getUsername();
            }

            @Override
            public boolean implies(Subject subject) {
                return false;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getAuthorities();
            }

            @Override
            public Object getCredentials() {
                return user.getPassword();
            }

            @Override
            public Object getDetails() {

                return user;
            }

            @Override
            public Object getPrincipal() {

                return user;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }
        };

        SecurityContextImpl securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
    }
}
