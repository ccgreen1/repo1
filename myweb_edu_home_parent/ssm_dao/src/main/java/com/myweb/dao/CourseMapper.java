package com.myweb.dao;

import com.myweb.domain.Course;
import com.myweb.domain.CourseVO;
import com.myweb.domain.Teacher;
import com.myweb.domain.UserTest;

import java.util.List;

public interface CourseMapper {
//    多条件课表查询
    public List<Course> findCourseByConditionForDao(CourseVO courseVO);

//    新增课程信息
    public void saveCourse(Course course);

//    新增讲师信息
    public void saveTeacher(Teacher teacher);

    //回显课程信息（包括课程的信息和讲师的部分信息）
    public CourseVO findCourseByIdForDao(int id);

    //跟新课程信息
    public void updateCourse(Course course);

//    跟新讲师信息
    public void updateTeacher(Teacher teacher);
//    课程管理状态
    public void updateCourseStatus(Course course);
}





