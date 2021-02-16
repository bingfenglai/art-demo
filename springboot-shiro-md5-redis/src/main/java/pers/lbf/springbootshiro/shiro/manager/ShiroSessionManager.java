package pers.lbf.springbootshiro.shiro.manager;


import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * <p>@author 赖柄沣 laibingf_dev@outlook.com</p>
 * <p>@date 2020/8/15 15:40</p>
 */
public class ShiroSessionManager extends DefaultWebSessionManager {

    //定义常量
    private static final String AUTHORIZATION = "Authorization";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
    //重写构造器
    public ShiroSessionManager() {
        super();
        this.setDeleteInvalidSessions(true);

    }

    /**
     * 重写方法实现从请求头获取Token便于接口统一
     *      * 每次请求进来,
     *      Shiro会去从请求头找Authorization这个key对应的Value(Token)
     * @param request
     * @param response
     * @return
     */
    @Override
    public Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中存在token 则从请求头中获取token
        if (!StringUtils.isEmpty(token)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        } else {

           return null;
        }
    }
}
