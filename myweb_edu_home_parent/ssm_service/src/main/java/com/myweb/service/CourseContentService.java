package com.myweb.service;

import com.myweb.domain.Course;
import com.myweb.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    public List<CourseSection> findCourseSectionAndLessonByCourseId(Integer courseId);

    public Course findCourseById(Integer courseId);

    public void saveSection(CourseSection courseSection);

//    跟新章节信息
    public void updateSection(CourseSection courseSection);

//    修改章节状态
    public void updateSectionStatus(Integer id,Integer status);




}
