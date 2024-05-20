package com.example.ITS;

import org.springframework.stereotype.Service;

@Service
public class CourseService {

    // 这里注入你的课程仓库（Course Repository）
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
