package com.myweb.service.impl;

import com.myweb.dao.UserTestMapper;
import com.myweb.domain.UserTest;
import com.myweb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//存到ioc容器中
@Service
public class TestServiceImpl implements TestService {

//    注入代理对象
    @Autowired
    private UserTestMapper userTestMapper;

    @Override
    public List<UserTest> selectAllforService() {
        List<UserTest> users = userTestMapper.selectAllforDao();
        return users;
    }
}
