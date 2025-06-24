package com.myweb.service;

import com.myweb.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {

    public List<ResourceCategory> findAllResourceCategory();

    //    添加资源分类信息
    public void saveResourceCategory(ResourceCategory resourceCategory);

    public void updateResourceCategory(ResourceCategory resourceCategory);

    public void deleteResourceCategoryById(Integer id);

    public ResourceCategory findResourceListByRoleId(Integer RoleId);

}
