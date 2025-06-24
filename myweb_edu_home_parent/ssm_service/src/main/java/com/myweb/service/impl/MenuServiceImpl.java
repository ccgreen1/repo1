package com.myweb.service.impl;

import com.myweb.dao.MenuMapper;
import com.myweb.domain.Menu;
import com.myweb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;



    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> list = menuMapper.findSubMenuListByPid(pid);
//        for (Menu menu : list) {
//            menu.getSubMenuList()
//        }
        return list;
    }

    //    查询所有菜单信息
    @Override
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }
}
