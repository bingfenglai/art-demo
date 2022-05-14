package pers.lbf.ssjr.productservice.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pers.lbf.ssjr.productservice.web.deserializer.CustomAuthorityDeserializer;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 用户凭证对象
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/3 16:20
 */
public class UserAuthVO implements Serializable {

    private String username;
    private List<SimpleGrantedAuthority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "UserAuthVO{" +
                "username='" + username + '\'' +
                ", authorityList=" + authorities +
                '}';
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.authorities;
    }
}
