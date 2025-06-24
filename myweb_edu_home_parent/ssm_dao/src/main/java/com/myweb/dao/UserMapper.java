package com.myweb.dao;

import com.myweb.domain.*;

import java.util.List;

public interface UserMapper {

//    用户分页&多条件组合查询
    public List<User> findAllUserByPage(UserVO userVO);

//    更新用户权限状态
    public void updateUserStatus(User user);

    public User login(User user);



//    删除该用户ID具有的角色信息（user_role_relation）
    public void deleteUserRelationRoleById(Integer id);

//    修改该用户ID具有的角色信息（user_role_relation）
    public void insertUserRelationRoleById(User_Role_relation user_role_relation);

    //    根据用户ID查询查询回显角色信息
    public List<Role> findUserRelationRoleById(Integer id);

//    根据角色信息（ID）查询角色所拥有的顶级菜单
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
//    根据pid，查询子菜单信息
    public List<Menu> findSubMenuByParentId(Integer pid);
//    获取用户拥有的资源权限信息(根据角色ID集合)
    public List<Resource> findResouurceByRoleId(List<Integer> ids);
}
