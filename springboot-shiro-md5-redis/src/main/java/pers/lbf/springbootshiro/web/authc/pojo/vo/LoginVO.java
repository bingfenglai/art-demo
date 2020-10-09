package pers.lbf.springbootshiro.web.authc.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/7 15:12
 */
@Data
public class LoginVO implements Serializable {

    private String username;
    private String password;

}
