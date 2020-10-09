package pers.lbf.springbootshiro.shiro.authcinfo;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/8 19:22
 */
public class MyAuthcInfo extends SimpleAuthenticationInfo implements Serializable {
    private String username;

    @Override
    public String toString() {
        return "MyAuthcInfo{" +
                "username='" + username + '\'' +
                ", principals=" + principals +
                ", credentials=" + credentials +
                ", credentialsSalt=" + credentialsSalt +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MyAuthcInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName) {
        super(principal, hashedCredentials, credentialsSalt, realmName);
    }
}
