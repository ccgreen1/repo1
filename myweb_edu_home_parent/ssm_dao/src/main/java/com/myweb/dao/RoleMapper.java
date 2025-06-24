package com.myweb.dao;

import com.myweb.domain.Role;
import com.myweb.domain.Role_menu_relation;
import com.myweb.domain.User_Role_relation;

import java.util.List;

public interface RoleMapper {

//    查询所有角色&条件查询(name)
//    虽然可以用String name来接收参数，但为了拓展性，可以用对象
    public List<Role> findAllRole(Role role);

//    根据角色ID查询该角色关联的额菜单信息ID
    public List<Integer> findMenuByRoleId(Integer roleId);

//    根据roleId清空中间表（role_resource_relation先前存的关联关系
    public void deleteRoleContextMenu(Integer roleid);

//    为角色分配菜单信息
    public void RoleContextMenu(Role_menu_relation roleMenuRelation);

//    根据角色ID删除角色信息
    public void deleteRole(Integer roleid);



}
