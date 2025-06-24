package com.myweb.service;

import com.github.pagehelper.PageInfo;
import com.myweb.domain.Resource;
import com.myweb.domain.ResourceVo;

import java.util.List;

public interface ResourceService {
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
