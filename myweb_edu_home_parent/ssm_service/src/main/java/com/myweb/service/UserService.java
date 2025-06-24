package com.myweb.service;

import com.github.pagehelper.PageInfo;
import com.myweb.domain.*;

import java.util.List;

public interface UserService {

//    用户分页查询（Service层主要实现分页）
    PageInfo<User> findAllUserByPage(UserVO userVO);

    void updateUserStatus(int id,String status);

    User login(User user) throws Exception;

    List<Role> findUserRelationRoleById(Integer id);

//    该用户ID关联具有的角色信息
    public void userContextRole(UserVO userVO);

//    获取用户权限，进行菜单动态展示
    public ResponseResult getUserPermissions(Integer userId);
}
