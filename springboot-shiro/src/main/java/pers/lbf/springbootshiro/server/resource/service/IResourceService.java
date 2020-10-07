package pers.lbf.springbootshiro.server.resource.service;

import pers.lbf.springbootshiro.entity.Resource;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 15:26
 */
public interface IResourceService {
    List<Resource> findByRoleIds(List<Long> roleIdList);
}
