package com.myweb.controller;

import com.myweb.domain.Course;
import com.myweb.domain.CourseSection;
import com.myweb.domain.CourseVO;
import com.myweb.domain.ResponseResult;
import com.myweb.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

//    根据课程id查询并回显关联的章节信息以及章节信息关联的课时信息
    @RequestMapping("/findCourseSectionAndLessonByCourseId")
    public ResponseResult findCourseSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentService.findCourseSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课时查询成功", list);
        return responseResult;
    }

//    回显章节对应的课程信息
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer courseId) {
        Course course = courseContentService.findCourseById(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "成功回显章节对应课程信息", course);
        return responseResult;
    }

//    新增或更新章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateCourseSection(@RequestBody CourseSection courseSection) {

//        1.判断是否携带章节ID
        try {
            if(courseSection.getId() == null){
                //        新增章节信息（insert）
                courseContentService.saveSection(courseSection);
                ResponseResult responseResult = new ResponseResult(true, 200, "新增章节成功", null);
                return responseResult;
            }else {
                //        修改章节信息（update）
                courseContentService.updateSection(courseSection);
                ResponseResult responseResult = new ResponseResult(true, 200, "更新章节成功", null);
                return responseResult;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    修改章节状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,Integer status) {
        courseContentService.updateSectionStatus(id,status);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true,200,"章节状态修改成功", map);
        return responseResult;
    }

}
