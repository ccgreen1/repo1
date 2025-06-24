package com.myweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myweb.dao.UserMapper;
import com.myweb.domain.*;
import com.myweb.service.UserService;
import com.myweb.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    //    用户分页查询（Service层主要实现分页）
    @Override
    public PageInfo<User> findAllUserByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());

        List<User> allUserByPage = userMapper.findAllUserByPage(userVO);
        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;

    }

    @Override
    public void updateUserStatus(int id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        Date date = new Date();
        user.setUpdate_time(date);
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {
//        user2是数据库中调出的信息，既密码是密文
        User user2 = userMapper.login(user);

//        当数据不为空 且 前端发来的数据与数据库根据前端发来的数据做查询操作调出的数据作对比 都成功时，密码正确，登录成功
        if (user2 != null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())) {
//            密码正确
            return user2;
        }else{
           return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    //    该用户ID关联具有的角色信息
    public void userContextRole(UserVO userVO) {
//        1.根据用户id删除该用户ID具有的角色信息
        userMapper.deleteUserRelationRoleById(userVO.getUserId());
//        2.再重新建立关联关系
//      遍历UserVo中的RoleIdList取出关联的每个要跟用户ID关联的角色ID,分装数据执行添加语句
        for(Integer roleId:userVO.getRoleIdList()){
            User_Role_relation userRoleRelation = new User_Role_relation();
            userRoleRelation.setUserId(userVO.getUserId());
            userRoleRelation.setRoleId(roleId);

            Date date = new Date();
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);

            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedby("system");
//            执行添加语句
            userMapper.insertUserRelationRoleById(userRoleRelation);
        }
    }

//    获取用户权限，进行菜单动态展示
    @Override
    public ResponseResult getUserPermissions(Integer userId) {
//        根据用户id获取当前用户拥有的角色(一对多(每个用户至少拥有一个角色))
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);
//        从集合返回值获取角色ID,保存在集合中供后面查询
        ArrayList<Integer> roleIds = new ArrayList<>();
        for(Role role:roleList){
            roleIds.add(role.getId());
        }
//        根据角色ID查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

//        再对父菜单关联的子菜单进行查询（返回在menu实体parentMenu中的setSubMenuList）
        for(Menu menu:parentMenu){
//            先遍历查询集合中每个元素的ParentId
            List<Menu> subMenu = userMapper.findSubMenuByParentId(menu.getId());
//            进行封装
            menu.setSubMenuList(subMenu);
        }
//        获取用户拥有的资源权限信息(根据角色ID集合)
        List<Resource> resouurceList = userMapper.findResouurceByRoleId(roleIds);

//        HashMap封装数据并返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenu);
        map.put("resouurceList", resouurceList);
        ResponseResult responseResult = new ResponseResult(true,200,"进行菜单动态展示成功",map);
        return responseResult;
    }



}
