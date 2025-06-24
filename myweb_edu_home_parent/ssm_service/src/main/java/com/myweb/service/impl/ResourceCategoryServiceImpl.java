package com.myweb.service.impl;

import com.myweb.dao.ReourceMapper;
import com.myweb.dao.ResourceCategoryMapper;
import com.myweb.domain.Resource;
import com.myweb.domain.ResourceCategory;
import com.myweb.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;
    @Autowired
    private ReourceMapper reourceMapper;


    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();
        return allResourceCategory;
    }

    //    添加资源分类信息
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");
        resourceCategoryMapper.insertResourceCategory(resourceCategory);
    }

    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {
        Date date = new Date();
        resourceCategory.setUpdatedTime(date);
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    @Override
    public void deleteResourceCategoryById(Integer id) {
        resourceCategoryMapper.deleteResourceCategoryById(id);
    }

    @Override
    public ResourceCategory findResourceListByRoleId(Integer RoleId) {

//        1.直接在dao中用<collection>获取元素
        ResourceCategory resourceListByRoleId = resourceCategoryMapper.findResourceListByRoleId(RoleId);
//        2调用dao中的两个方法分别获取资源分类数据和资源数据 将资源数据封装到对应的分类下
//        List<Resource> resourceList = reourceMapper.findResourceBycategoryId(resourceListByRoleId.getSort());
//        resourceListByRoleId.setResourceList(resourceList);
        return resourceListByRoleId;

    }
}
