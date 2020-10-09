package pers.lbf.springbootshiro.entity;


import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-10-06 15:39:02
 */
public class User  implements Serializable  {
  private Long userId;

  private String username;

  private String password;

  private String salt;

  @Override
  public String toString() {
    return "User{" +
            "userId=" + userId +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", salt='" + salt + '\'' +
            '}';
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
}
