package com.myweb.service;

import com.myweb.domain.Course;
import com.myweb.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    public List<Course> findCourseByConditionForService(CourseVO courseVO);

    public void saveCourseTeacherForService(CourseVO courseVO);

    public CourseVO findCourseByIdForService(int id);

//    更新课程和讲师信息
    public void updateCourseTeacherForService(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

//    更新课程状态
    public void updateCourseStatusForService(int courseId, int status) ;


}
