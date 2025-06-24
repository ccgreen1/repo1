package com.myweb.controller;


import com.myweb.domain.UserTest;
import com.myweb.service.impl.TestServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController//@Controller +@ResponseBody
@RequestMapping("/test")
public class TestController {

//    注入service
    @Autowired
    private TestServiceImpl testServiceImpl;


    @RequestMapping("/findAllTest")
    public List<UserTest> findAll1() {
//        1.调用Service方法
        List<UserTest> userTests = testServiceImpl.selectAllforService();
        return userTests;
    }


    @PostMapping("/postfile")
    public String paramlfile(@RequestParam MultipartFile file) throws IOException {

//        文件复制
        InputStream inputStream = file.getInputStream();//输入流
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\Citra\\" + file.getOriginalFilename());
//        执行文件拷贝
        IOUtils.copy(inputStream, fileOutputStream);
        System.out.println("拷贝成功");
//        关闭流
        inputStream.close();
        fileOutputStream.close();
        return null;
    }

    @RequestMapping("1")
    public void show(){
        System.out.println("test中文");
    }

}
