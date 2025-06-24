package com.myweb.controller;


import com.myweb.domain.*;
import com.myweb.service.MenuService;
import com.myweb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/fingAllRole")
    public ResponseResult fingAllRole(@RequestBody Role role) {
        List<Role> list = roleService.findAllRole(role);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所欲信息成功", list);
        return responseResult;

    }

    //    查询菜单信息：分配菜单的第一个接口(一表父子菜单关联)
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid() {
//        -1表示查询所有的父级菜单
        List<Menu> menulist = menuService.findSubMenuListByPid(-1);

//        相应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList", menulist);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有的父子菜单成功", map);
        return responseResult;
    }

    //    根据角色ID查询该角色关联的额菜单信息ID
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {
        List<Integer> list = roleService.findMenuByRoleId(roleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色关联的菜单信息成功", list);
        return responseResult;
    }

//    为角色分配菜单
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo) {
        roleService.RoleContextMenu(roleMenuVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "角色从新分配菜单成功", null);
        return responseResult;
    }
//    删除roles表中的角色
    @RequestMapping("/DeleteRole")
    public ResponseResult DeleteRole(Integer id) {
        roleService.deleteRole(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);
        return responseResult;
    }

//    为角色分配菜单(添加& 修改资源分类接口)
    @RequestMapping("/roleContextResource")
    public ResponseResult findResourceListByRoleId(@RequestBody RoleResourceVo roleResourceVo) {
       roleService.findResourceListByRoleId(roleResourceVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "角色从新分配资源菜单成功", null);
        return responseResult;
    }



}
