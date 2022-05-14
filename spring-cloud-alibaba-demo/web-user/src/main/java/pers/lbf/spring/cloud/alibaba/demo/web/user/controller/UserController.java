package pers.lbf.spring.cloud.alibaba.demo.web.user.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.spring.cloud.alibaba.demo.service.interfaces.IUserService;
import pers.lbf.spring.cloud.alibaba.demo.service.pojo.dto.RpcResultDTO;
import pers.lbf.spring.cloud.alibaba.demo.service.pojo.dto.user.UserSimpleInfoDTO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0 @Description TODO
 * @date 2020/11/23 23:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @DubboReference(version = "1.0")
    private IUserService userService;

    @GetMapping("/getUserInfo")
    public String getUserInfo() {
        RpcResultDTO<UserSimpleInfoDTO> result = userService.getUserInfo();
        return result.toString();
    }
}
