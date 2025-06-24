package com.myweb.controller;


import com.myweb.domain.Course;
import com.myweb.domain.CourseVO;
import com.myweb.domain.ResponseResult;//后端响应前端传送到网页的Json数据格式
import com.myweb.service.CourseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private static final Log log = LogFactory.getLog(CourseController.class);
    @Autowired
    private CourseService courseService;


    //    根据ID查询课程信息
    @RequestMapping("/findCourseByConditionForWeb")
    public ResponseResult findCourseByConditionForWeb(@RequestBody CourseVO courseVO) {
        List<Course> list = courseService.findCourseByConditionForService(courseVO);


//        响应封装
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",list);
//        把响应对象响应给前端展示（给Servlet做出不同动作）
        System.out.println("scssess");
        return responseResult;

    }

    @RequestMapping("/coursepicturesupload")
    public ResponseResult fileupload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {


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


    //    注意新增和修改要写在同一个方法中
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO)throws InvocationTargetException, IllegalAccessException {

//        判断执行新增还是更改(没有VO中的ID就是要新增)
       if(courseVO.getId() == null){
           //调用Service(执行新增)
           courseService.saveCourseTeacherForService(courseVO);
           ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
           return responseResult;
       }else{
           courseService.updateCourseTeacherForService(courseVO);
           ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
           return responseResult;
       }
    }

//    根据ID查询具体的课程信息及关联的讲师信息
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseByIdForService(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "根据ID查询信息成功", courseVO);
        return responseResult;
    }
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        courseService.updateCourseStatusForService(id,status);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态更新成功", map);
        return responseResult;
    }
}
