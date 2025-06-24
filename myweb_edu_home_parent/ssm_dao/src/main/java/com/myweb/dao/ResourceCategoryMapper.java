package com.myweb.dao;

import com.myweb.domain.Resource;
import com.myweb.domain.ResourceCategory;
import com.myweb.domain.Role_resource_relation;

import java.util.List;

public interface ResourceCategoryMapper {


//    查询所有资源分类信息
    public List<ResourceCategory> findAllResourceCategory();

    //    添加资源分类信息
    public void insertResourceCategory(ResourceCategory resourceCategory);

    //    修改资源分类信息
    public void updateResourceCategory(ResourceCategory resourceCategory);

//    删除资源分类信息
    public void deleteResourceCategoryById(Integer id);


//    获取当前角色拥有的资源信息
    public ResourceCategory findResourceListByRoleId(Integer id);

//    为角色分配菜单(添加& 修改资源分类接口)
//
//    1.删除关系表中的关系根据roleId
    public void deleteResouceContextByRoleId(Integer RoleId);

//    2.为角色分配资源接口
    public void roleContextResouceByResourceId(Role_resource_relation role_resource_relation);

}
