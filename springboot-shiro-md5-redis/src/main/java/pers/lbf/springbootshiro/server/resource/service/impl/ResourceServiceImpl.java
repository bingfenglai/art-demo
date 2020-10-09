package pers.lbf.springbootshiro.server.resource.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lbf.springbootshiro.entity.Resource;
import pers.lbf.springbootshiro.server.resource.dao.IResourceDao;
import pers.lbf.springbootshiro.server.resource.service.IResourceService;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 15:27
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private IResourceDao resourceDao;

    @Override
    public List<Resource> findByRoleIds(List<Long> roleIdList) {
        if (roleIdList == null || roleIdList.isEmpty()) {
            return null;
        }

        List<Long> resourceIds = resourceDao.findResourceIdsByRoleIds(roleIdList);
        if (resourceIds.size() == 0) {
            return null;
        }

        return resourceDao.findAllById(resourceIds);

    }
}
