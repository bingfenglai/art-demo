package pers.lbf.springbootspringsecuriotydemo1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lbf.springbootspringsecuriotydemo1.dao.IPermissonDao;
import pers.lbf.springbootspringsecuriotydemo1.dao.IRoleDao;
import pers.lbf.springbootspringsecuriotydemo1.pojo.PermissionDO;
import pers.lbf.springbootspringsecuriotydemo1.pojo.RoleDO;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/9/1 20:58
 */
@SpringBootTest
public class RoleTest {
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IPermissonDao permissonDao;

    @Test
    public void test1() {
        List<RoleDO> roleList = roleDao.findByUserId(4);
        System.out.println(roleList);
        for (RoleDO roleDO : roleList) {
            List<PermissionDO> byRoleId = permissonDao.findByRoleId(roleDO.getId());
            for (PermissionDO permissionDO : byRoleId) {
                System.out.println(permissionDO.toString());
            }
        }
    }
}
