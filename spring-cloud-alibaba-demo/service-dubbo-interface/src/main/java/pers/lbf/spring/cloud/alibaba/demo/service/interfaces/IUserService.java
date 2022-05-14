package pers.lbf.spring.cloud.alibaba.demo.service.interfaces;

import pers.lbf.spring.cloud.alibaba.demo.service.pojo.dto.RpcResultDTO;
import pers.lbf.spring.cloud.alibaba.demo.service.pojo.dto.user.UserSimpleInfoDTO;

/**
 * 用户服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description TODO
 * @date 2020/11/23 22:33
 */
public interface IUserService {

    RpcResultDTO<UserSimpleInfoDTO> getUserInfo();
}
