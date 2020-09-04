package pers.lbf.ssjr.authenticationservice.pojo.vo;

import java.io.Serializable;

/**用户登录请求参数对象
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/3 16:16
 */
public class UserLoginVo implements Serializable {

    private String username;
    private String password;

    @Override
    public String toString() {
        return "UserLoginVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
