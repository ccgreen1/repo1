package com.myweb.test;

import com.myweb.dao.UserTestMapper;
import com.myweb.domain.UserTest;
import com.myweb.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class MybatisTest01 {

    public static void main(String[] args) throws IOException {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();


        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserTestMapper mapper = sqlSession.getMapper(UserTestMapper.class);
        List<UserTest> userTests = mapper.selectAllforDao();
        for (UserTest userTest : userTests) {
            System.out.println(userTest);
        }

        sqlSession.close();

    }
}
