package pers.lbf.springbootshiro.entity;


import java.io.Serializable;

/**
 * 角色表(Role)实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-10-06 15:37:16
 */

public class Role implements Serializable {
  private static final long serialVersionUID = 534384732226656028L;


  private Long roleId;


  private String roleName;


  private String roleDesc;


  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleDesc() {
    return roleDesc;
  }

  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }

  @Override
  public String toString() {
    return "Role{" +
            "roleId=" + roleId +
            ", roleName='" + roleName + '\'' +
            ", roleDesc='" + roleDesc + '\'' +
            '}';
  }
}
