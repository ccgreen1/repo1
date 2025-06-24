package com.myweb.dao;

import com.myweb.domain.Resource;
import com.myweb.domain.ResourceVo;

import java.util.List;

public interface ReourceMapper {
//    分页&多条件查询资源
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);

    public List<Resource> findResourceBycategoryId(Integer categoryId);







}
