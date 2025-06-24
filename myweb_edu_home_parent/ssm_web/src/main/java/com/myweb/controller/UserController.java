package com.myweb.controller;


import com.github.pagehelper.PageInfo;
import com.myweb.domain.ResponseResult;
import com.myweb.domain.Role;
import com.myweb.domain.User;
import com.myweb.domain.UserVO;
import com.myweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    //    用户分页查询(返回数据给前端)
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO) {
        PageInfo<User> pageInfo = userService.findAllUserByPage(userVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "用户分页查询成功", pageInfo);
        return responseResult;
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status) {
        userService.updateUserStatus(id,status);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", status);
        ResponseResult responseResult = new ResponseResult(true, 200, "用户状态更新成功", map);
        return responseResult;
    }

//    用户登录
    @RequestMapping("/login")
    public ResponseResult login(@RequestBody User user, HttpServletRequest request) throws Exception {
        User login = userService.login(user);

        if (login != null) {
//            保存用户id及access_token到session中（用户发送下一次请求时，与session中的值作对比，不用重新登录）
            HttpSession session = request.getSession();
            String s = UUID.randomUUID().toString();
            session.setAttribute("access_token",s);
            session.setAttribute("user_id",login.getId());

//            将获得的数据装换成json格式发送到前端
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token",s);
            map.put("user_id",login.getId());
            ResponseResult responseResult = new ResponseResult(true, 200, "登录成功",map);
            return responseResult;
        }else {
            ResponseResult responseResult = new ResponseResult(true, 400, "登录失败",null);
            return responseResult;
        }
    }

//    分配角色回显查询
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id) {
        List<Role> roleList = userService.findUserRelationRoleById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "回显成功",roleList);
        return responseResult;
    }

    //    该用户ID关联具有的角色信息
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(UserVO userVO) {
        userService.userContextRole(userVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "分配角色成功",null);
        return responseResult;
    }

//    获取用户权限，进行菜动态展示
    public ResponseResult getUserPermissions(HttpServletRequest request) {
//        1.获取请求头中的token
        String head_token = request.getHeader("Authorization");
//        2.获取session中的token
        String session_token = (String) request.getAttribute("access_token");
//        3.判断token是否一致
        if(head_token.equals(session_token)) {
//            同一用户且在登录状态
//            1.获取用户id
            Integer userId = (Integer) request.getSession().getAttribute("user_id");
//            2.调用srevice进行菜单信息查询、
            ResponseResult responseResult = userService.getUserPermissions(userId);
            return responseResult;
        }else{
//            没有登录状态
            ResponseResult responseResult = new ResponseResult(false, 400, "获取菜单信息失败", null);
            return responseResult;
        }

    }

}
