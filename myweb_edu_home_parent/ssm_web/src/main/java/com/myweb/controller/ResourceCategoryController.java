package com.myweb.controller;


import com.myweb.domain.ResourceCategory;
import com.myweb.domain.ResponseResult;
import com.myweb.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有分类信息成功",allResourceCategory);
        return responseResult;
    }


    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        if(resourceCategory.getId() == null){

            resourceCategoryService.saveResourceCategory(resourceCategory);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增分类信息成功",null);
            return responseResult;

        }else {
            resourceCategoryService.updateResourceCategory(resourceCategory);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改分类信息成功",null);
            return responseResult;
        }
    }

    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCategoryService.deleteResourceCategoryById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除分类信息成功",null);
        return responseResult;
    }

//    获取当前角色拥有的资源信息
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer RoleId){
        ResourceCategory list = resourceCategoryService.findResourceListByRoleId(RoleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "获取当前角色拥有的资源信息成功",list);
        return responseResult;
    }


}
