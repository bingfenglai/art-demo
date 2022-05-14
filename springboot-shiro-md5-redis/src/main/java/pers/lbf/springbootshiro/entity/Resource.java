package pers.lbf.springbootshiro.entity;


import java.io.Serializable;

/**
 * 权限菜单表（资源表）(Resource)实体类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2020-10-07 00:23:46
 */

public class Resource implements Serializable {
    private static final long serialVersionUID = -80603043571024051L;
    /**
     * 主键
     */

    private Long resourceId;
    /**
     * 资源名称
     */

    private String resourceName;
    /**
     * 资源uri
     */

    private String resourceUri;
    /**
     * 父资源id
     */

    private Long resourceFartherId;
    /**
     * 资源图标路径
     */

    private String resourceIcoUrl;
    /**
     * 描述
     */

    private String resourceDesc;
    /**
     * 权限标识符
     */

    private String resourcePermissionTag;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public Long getResourceFartherId() {
        return resourceFartherId;
    }

    public void setResourceFartherId(Long resourceFartherId) {
        this.resourceFartherId = resourceFartherId;
    }

    public String getResourceIcoUrl() {
        return resourceIcoUrl;
    }

    public void setResourceIcoUrl(String resourceIcoUrl) {
        this.resourceIcoUrl = resourceIcoUrl;
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    public String getResourcePermissionTag() {
        return resourcePermissionTag;
    }

    public void setResourcePermissionTag(String resourcePermissionTag) {
        this.resourcePermissionTag = resourcePermissionTag;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", resourceUri='" + resourceUri + '\'' +
                ", resourceFartherId=" + resourceFartherId +
                ", resourceIcoUrl='" + resourceIcoUrl + '\'' +
                ", resourceDesc='" + resourceDesc + '\'' +
                ", resourcePermissionTag='" + resourcePermissionTag + '\'' +
                '}';
    }
}
