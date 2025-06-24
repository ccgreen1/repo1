package com.myweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myweb.dao.ReourceMapper;
import com.myweb.domain.Resource;
import com.myweb.domain.ResourceVo;
import com.myweb.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ReourceMapper reourceMapper;

    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo) {
//        分页查询
        PageHelper.startPage(resourceVo.getCurrentPage(), resourceVo.getPageSize());

        List<Resource> allResourceByPage = reourceMapper.findAllResourceByPage(resourceVo);

        PageInfo<Resource> pageInfo = new PageInfo<>(allResourceByPage);
        return pageInfo;
    }
}
