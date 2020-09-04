package pers.lbf.ssjr.authenticationservice.pojo.to;

import java.io.Serializable;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/1 21:27
 */
public class PermissionDO implements Serializable {
    private Integer id;
    private String permissionName;
    private String permissionUrl;
    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }


    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    @Override
    public String toString() {
        return "PermissionDO{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", permissionUrl='" + permissionUrl + '\'' +
                ", parentId=" + parentId +
                '}';
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
