package com.example.ITS;

import org.springframework.stereotype.Service;

import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.CourseCategory;
import com.example.ITS.Repository.CourseCategoryRepository;
import com.example.ITS.Repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseCategoryRepository courseCategoryRepository;

    public CourseService(CourseRepository courseRepository, CourseCategoryRepository courseCategoryRepository) {
        this.courseRepository = courseRepository;
        this.courseCategoryRepository = courseCategoryRepository;
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

    public CourseCategory getCategory(Long id) {
        return courseCategoryRepository.findById(id).orElse(null);
    }

    public CourseCategory saveCategory(CourseCategory category) {
        return courseCategoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        courseCategoryRepository.deleteById(id);
    }

    public Iterable<CourseCategory> getAllCategories() {
        return courseCategoryRepository.findAll();
    }
}
