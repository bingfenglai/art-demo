package pers.lbf.springbootshiro.server.resource.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.lbf.springbootshiro.entity.Resource;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/10/6 15:28
 */
public interface IResourceDao  {

    @Select("<script>"+
            "select resource_id from role_resource where role_id in"+
            "<foreach item='item' index='index' collection='roleIdList' open='(' separator=',' close=')'> "+
            "#{item}"+
            "</foreach>"+
            "</script>")
    List<Long> findResourceIdsByRoleIds( @Param("roleIdList") List<Long> roleIdList);

    @Select({"<script>",
            "select * from resource where resource_id in " ,
            "<foreach item='item' index='index' collection='resourceIds' open='(' separator=',' close=')'> ",
            "#{item}",
            "</foreach>",
            "</script>"})
    List<Resource> findAllById(@Param("resourceIds") List<Long> resourceIds);
}
