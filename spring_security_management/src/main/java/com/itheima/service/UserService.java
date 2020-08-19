package com.itheima.service;

import com.itheima.domain.SysUser;

import java.util.List;
import java.util.Map;

public interface UserService {

    public void save(SysUser user);

    public List<SysUser> findAll();

    public Map<String, Object> toAddRolePage(Integer id);

    public void addRoleToUser(Integer userId, Integer[] ids);
}
