package com.myweb.service.impl;

import com.myweb.dao.ResourceCategoryMapper;
import com.myweb.dao.RoleMapper;
import com.myweb.domain.*;
import com.myweb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;


//    查询所有角色
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> list = roleMapper.findAllRole(role);
        return list;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleiId) {
        List<Integer> list = roleMapper.findMenuByRoleId(roleiId);
        return list;
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
//        清空中间表之前权限记录（roleId与menuId之前的关系绑定）
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleid());
//        为角色分配菜单id
//        1.遍历menuIdList取出每个menuId
//        2.与roleId绑定一次关系添加到user_role_relation实体类中
//        3.执行mapper将数据上传到User_Role_relation表中
        for (Integer mid:roleMenuVo.getMenuIdList()){
            Role_menu_relation roleMenuRelation = new Role_menu_relation();
//            封装数据
            roleMenuRelation.setMenuId(mid);
            roleMenuRelation.setRoleId(roleMenuVo.getRoleid());
            Date date = new Date();
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setUpdatedTime(date);
            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedby("system");

//            执行mapper
            roleMapper.RoleContextMenu(roleMenuRelation);
        }

    }

    @Override
    public void deleteRole(Integer roleid) {
//        清空关联关系
        roleMapper.deleteRoleContextMenu(roleid);
        roleMapper.deleteRole(roleid);
    }


    @Override
    public void findResourceListByRoleId(RoleResourceVo roleResourceVo) {
        //        清空中间表之前权限记录（roleId与resourceId之前的关系绑定）
        resourceCategoryMapper.deleteResouceContextByRoleId(roleResourceVo.getRoleId());

        for(Integer mid:roleResourceVo.getResourceIdList()){
            Role_resource_relation roleResourceRelation = new Role_resource_relation();
//            封装数据到关系表实体类
            roleResourceRelation.setResourceId(mid);
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());

            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");
//            执行sql添加语句
            resourceCategoryMapper.roleContextResouceByResourceId(roleResourceRelation);

        }

    }
}
