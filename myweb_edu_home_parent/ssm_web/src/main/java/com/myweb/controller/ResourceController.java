package com.myweb.controller;


import com.github.pagehelper.PageInfo;
import com.myweb.domain.Resource;
import com.myweb.domain.ResourceVo;
import com.myweb.domain.ResponseResult;
import com.myweb.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResourceByPage")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVo resourceVo) {
        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "资源信息分页多条件查询成功", pageInfo);
        return responseResult;
    }



}
