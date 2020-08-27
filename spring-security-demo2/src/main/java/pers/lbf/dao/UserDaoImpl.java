package pers.lbf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.lbf.pojo.UserDO;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/8/23 10:28
 */
@Mapper
public interface UserDaoImpl extends BaseMapper<UserDO> {
}
