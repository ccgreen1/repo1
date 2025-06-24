package com.myweb.controller;


import com.myweb.domain.Menu;
import com.myweb.domain.ResponseResult;
import com.myweb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;



    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> list = menuService.findAllMenu();
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有菜单信息成功", list);
        return responseResult;
    }

//    回显对应菜单信息
    @RequestMapping("/findMenuById")
    public ResponseResult findMenuById(Integer id,Integer lazybutton) {
//        根据id值判断当前是更新还是添加操作 判断id的值是否是-1
        if (id == -1) {
            //添加，不需要查询menu信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
//            封装数据

            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            ResponseResult responseResult = new ResponseResult(true, 200, "添加回显成功", map);
            return responseResult;
        }else {
//         id!=-1 进行修改操作 回显所有menu信息（id）
            Menu menu = menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList", subMenuListByPid);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改回显成功", map);
            return responseResult;


        }

    }









}

