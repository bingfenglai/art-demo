package pers.lbf.spring.cloud.alibaba.demo.service.pojo.dto.user;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description TODO
 * @date 2020/11/23 22:53
 */
public class UserSimpleInfoDTO implements Serializable {

  private String userName;
  private String gender;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserSimpleInfoDTO that = (UserSimpleInfoDTO) o;
    return Objects.equals(userName, that.userName) && Objects.equals(gender, that.gender);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, gender);
  }

  @Override
  public String toString() {
    return "UserSimpleInfoDTO{"
        + "userName='"
        + userName
        + '\''
        + ", gender='"
        + gender
        + '\''
        + '}';
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
