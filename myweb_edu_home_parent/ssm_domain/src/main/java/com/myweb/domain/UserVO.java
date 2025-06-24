package com.myweb.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class UserVO {

    public Integer currentPage;
    public Integer pageSize;
    public String username;

    private Integer userId;
    private List<Integer> RoleIdList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getRoleIdList() {
        return RoleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        RoleIdList = roleIdList;
    }

    //    前端传送过来的数据是2020-2-22,Data类的格式是2020/2/22 用注解装换即可
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date endCreateTime;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", RoleIdList=" + RoleIdList +
                ", createTime=" + createTime +
                ", endCreateTime=" + endCreateTime +
                '}';
    }
}
