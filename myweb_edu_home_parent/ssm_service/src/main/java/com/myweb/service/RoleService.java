package com.myweb.service;

import com.myweb.domain.Role;
import com.myweb.domain.RoleMenuVo;
import com.myweb.domain.RoleResourceVo;
import com.myweb.domain.User_Role_relation;

import java.util.List;

public interface RoleService {
    //    查询所有角色
    public List<Role> findAllRole(Role role);

    List<Integer> findMenuByRoleId(Integer roleid);

    //    为角色分配菜单信息&清空中间表
    public void RoleContextMenu(RoleMenuVo roleMenuVo);

//    删除角色
    public void deleteRole(Integer roleid);


    public void findResourceListByRoleId(RoleResourceVo roleResourceVo);

}
