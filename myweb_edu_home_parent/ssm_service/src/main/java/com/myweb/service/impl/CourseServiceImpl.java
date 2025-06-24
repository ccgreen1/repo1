package com.myweb.service.impl;

import com.myweb.dao.CourseMapper;
import com.myweb.domain.Course;
import com.myweb.domain.CourseVO;
import com.myweb.domain.Teacher;
import com.myweb.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public List<Course> findCourseByConditionForService(CourseVO courseVO) {
        List<Course> list = courseMapper.findCourseByConditionForDao(courseVO);
        System.out.println("Service层调用Dao方法");
        return list;
    }

    @Override
    public void saveCourseTeacherForService(CourseVO courseVO) {
//        封装课程信息(把courseVO封装进Course)
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVO);

//        补全课程信息(时间)
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保存课程信息
         courseMapper.saveCourse(course);

         //获取新插入数据的id值
        int id = course.getId();

        //封装讲师的信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVO);

//        补全讲师的信息（创建日期，是否被删除 ，对应的课程id）
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        //保存讲师信息(调用语句)
        courseMapper.saveTeacher(teacher);


    }

//    根据ID查询课程信息
    public CourseVO findCourseByIdForService(int id) {
        CourseVO courseById = courseMapper.findCourseByIdForDao(id);
        return courseById;
    }

    @Override
    public void updateCourseTeacherForService(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException{
//        封装课程
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVO);

//        补全信息（更新日期）
        Date date = new Date();
        course.setUpdateTime(date);
//        保存课程信息
        courseMapper.updateCourse(course);

//        讲师
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVO);
//        设置对应的id
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatusForService(int courseId, int status) {
//       封装
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        Date date = new Date();
        course.setUpdateTime(date);
//        调用mapper
        courseMapper.updateCourseStatus(course);
    }



}
