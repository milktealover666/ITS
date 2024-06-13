package com.example.ITS.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ITS.Entity.Course;
import com.example.ITS.Repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // 添加课程
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // 修改课程
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    // 删除课程
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // 查询所有课程
    public List<Course> findAllCourses() {
        Iterable<Course> courses = courseRepository.findAll();
        List<Course> courseList = new ArrayList<>();
        courses.forEach(courseList::add);
        return courseList;
    }

    // 根据 ID 查询课程
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }


}
