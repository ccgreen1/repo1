package com.myweb.dao;

import com.myweb.domain.UserTest;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserTestMapper {

//    查询所有
      List<UserTest> selectAllforDao();
}
