package com.myweb.controller;


import com.github.pagehelper.PageInfo;
import com.myweb.domain.PromotionAd;
import com.myweb.domain.PromotionAdVO;
import com.myweb.domain.ResponseResult;
import com.myweb.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    //广告分页查询
    @RequestMapping("/findAllPromotionAdByPage")
//    @RequestBody:获取请求体的信息并封装到实体中 没有返回就不写
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", pageInfo);
        return responseResult;
    }

//    @RequestParam 获取 URL 上的值(如果不存在，使用默认值):@RequestParam(value = "id", defaultValue = "name")
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam Integer id) {
        try {
            PromotionAd promotionAd = promotionAdService.findPromotionAdByIdForService(id);

            ResponseResult responseResult = new ResponseResult(true, 200, "广告信息回显成功", promotionAd);
            return responseResult;
        } catch (Exception e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            return null;
        }
    }


    /*
新增或更新广告位置
*/

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        try {
            if (promotionAd.getId() == null) {
                Date date = new Date();
                promotionAd.setCreateTime(date);
                promotionAd.setUpdateTime(date);

                promotionAdService.savePromotionAd(promotionAd);
                ResponseResult result = new ResponseResult(true, 200, "新建成功",
                        null);
                return result;
            } else {
                Date date = new Date();
                promotionAd.setUpdateTime(date);
                promotionAdService.updatePromotionAd(promotionAd);
                ResponseResult result = new ResponseResult(true, 200, "更新成功",
                        null);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status) {
    promotionAdService.updatePromotionAdStatus(id, status);

    HashMap<String, Integer> map = new HashMap<>();
    map.put("status", status);

    ResponseResult responseResult = new ResponseResult(true, 200, "广告动态修改成功", map);
    return responseResult;
}




//    图片上传
@RequestMapping("/PromotionAdUpload")
public ResponseResult fileupload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {


//        1.判断接收到的上传文件是否为空
    if(file.isEmpty()){
        throw new RuntimeException();
    }
//        2.获取项目部署路径
//        D:\apache-tomcat-9.0.106\webapps\ssm-web\
    String realPath = request.getServletContext().getRealPath("/");
    System.out.println("realPath:"+realPath);
//        D:\apache-tomcat-9.0.106\webapps
    String substring = realPath.substring(0, realPath.indexOf("ssm-web"));
    System.out.println("substring:"+substring);
//        3.获取原文件名
    String originalFilename = file.getOriginalFilename();
    System.out.println("originalFilename:"+originalFilename);
//        4.生成新文件名
    String newFilename = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
    System.out.println("newFilename:"+newFilename);
//        5.文件上传
//        D:\apache-tomcat-9.0.106\webapps\ upload\
    String uploadPath = substring + "upload\\";
    System.out.println("uploadPath:"+uploadPath);
    File filePath = new File(uploadPath, newFilename);
    System.out.println("filePath:"+filePath);

//        如果目录不存在就创建一个upload目录
    if(!filePath.getParentFile().exists()){
        filePath.getParentFile().mkdirs();
        System.out.println("创建目录："+filePath);
    }


//        图片就进行了真正的上传
    file.transferTo(filePath);

//        6.将文件名和文件路径返回，进行响应
    HashMap<String,String> map = new HashMap<>();
    map.put("fileName",newFilename);
//        filePath:http://localhost:8080/upload/xxxx.JPG
    map.put("filePath","http://localhost:8080/upload/"+newFilename);

    ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
    System.out.println("上传成功");

    return responseResult;
}




}
