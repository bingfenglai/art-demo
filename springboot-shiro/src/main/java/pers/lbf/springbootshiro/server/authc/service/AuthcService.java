package pers.lbf.springbootshiro.server.authc.service;

import org.apache.shiro.authc.AuthenticationException;
import pers.lbf.springbootshiro.web.authc.pojo.vo.LoginVO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/7 15:14
 */
public interface AuthcService {
    boolean login(LoginVO loginVO) throws AuthenticationException;
}
