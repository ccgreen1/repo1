package com.myweb.service.impl;

import com.myweb.dao.CourseContentMapper;
import com.myweb.domain.Course;
import com.myweb.domain.CourseSection;
import com.myweb.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceimpl implements CourseContentService {
    
    @Autowired
    private CourseContentMapper courseContentMapper;


    @Override
    public List<CourseSection> findCourseSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return list;
    }

    @Override
    public Course findCourseById(Integer courseId) {
        Course course = courseContentMapper.findCourseById(courseId);
        return course;
    }

    @Override
    public void saveSection(CourseSection courseSection) {
//        补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        courseSection.setUpdateTime(new Date());
         courseContentMapper.updateSection(courseSection);


    }

    @Override
    public void updateSectionStatus(Integer id,Integer status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(courseSection);
    }


}
