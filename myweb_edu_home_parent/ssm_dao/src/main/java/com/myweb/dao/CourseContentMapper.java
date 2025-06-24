package com.myweb.dao;

import com.myweb.domain.Course;
import com.myweb.domain.CourseLesson;
import com.myweb.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
//    根据课程id查询关联的章节信息及章节信息关联的课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);
//    回显章节对应的课程信息
    public Course findCourseById(Integer courseId);

    public void saveSection(CourseSection courseSection);

    public void updateSection(CourseSection courseSection);

    public void updateSectionStatus(CourseSection courseSection);
}
