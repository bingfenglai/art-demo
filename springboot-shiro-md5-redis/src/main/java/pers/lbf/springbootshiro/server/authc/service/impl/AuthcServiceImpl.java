package pers.lbf.springbootshiro.server.authc.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import pers.lbf.springbootshiro.server.authc.service.AuthcService;
import pers.lbf.springbootshiro.web.authc.pojo.vo.LoginVO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/7 15:15
 */
@Service
public class AuthcServiceImpl implements AuthcService {
    @Override
    public boolean login(LoginVO loginVO) throws AuthenticationException {
        if (loginVO == null) {
            return false;
        }

        if (loginVO.getUsername() == null || "".equals(loginVO.getUsername())) {
            return false;
        }

        if (loginVO.getPassword() == null || "".equals(loginVO.getPassword())) {
            return false;
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVO.getUsername(), loginVO.getPassword());


        subject.login(token);


        return true;
    }
}
