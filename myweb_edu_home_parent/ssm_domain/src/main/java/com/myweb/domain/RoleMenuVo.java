package com.myweb.domain;

import java.util.List;

public class RoleMenuVo {
    private Integer roleid;
    private List<Integer> menuIdList;

    @Override
    public String toString() {
        return "RoleMenuVo{" +
                "roleid=" + roleid +
                ", menuIdList=" + menuIdList +
                '}';
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
