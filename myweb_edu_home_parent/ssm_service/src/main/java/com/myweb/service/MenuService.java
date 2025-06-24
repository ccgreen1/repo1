package com.myweb.service;


import com.myweb.domain.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> findSubMenuListByPid(int pid);
//    查询所有菜单信息
    public List<Menu> findAllMenu();

    public Menu findMenuById(Integer id);
}
