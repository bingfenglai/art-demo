package pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.service;

import org.springframework.stereotype.Service;
import pers.lbf.spring.cloud.alibaba.demo.sentinel.demo.vo.ResultVO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/31 9:23
 */
@Service
public class DemoService {


    public ResultVO getInfo(){

        return new ResultVO<>("wxb: Java开发实践");
    }




}
