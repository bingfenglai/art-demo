package pers.lbf.spring.cloud.alibaba.demo.service.publisher.dao;

import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/23 20:04
 */

public interface IConfigInfoDao {

    @Select("select * from config_info where id=1")
    HashMap selectInfo();
}
