package pers.lbf.spring.cloud.alibaba.demo.service.dubbo.user.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import pers.lbf.spring.cloud.alibaba.demo.service.interfaces.IUserService;
import pers.lbf.spring.cloud.alibaba.demo.service.pojo.dto.RpcResultDTO;
import pers.lbf.spring.cloud.alibaba.demo.service.pojo.dto.user.UserSimpleInfoDTO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description TODO
 * @date 2020/11/23 23:00
 */
@DubboService(interfaceClass = IUserService.class, version = "1.0")
public class UserServiceImpl implements IUserService {

    @Override
    public RpcResultDTO<UserSimpleInfoDTO> getUserInfo() {

        UserSimpleInfoDTO userInfo = new UserSimpleInfoDTO();
        userInfo.setGender("男");
        userInfo.setUserName("向北");
        return new RpcResultDTO<>(userInfo);
    }
}
