package com.example.ITS.Service;

import org.springframework.stereotype.Service;

import com.example.ITS.Entity.CourseCategory;
import com.example.ITS.Repository.CourseCategoryRepository;

@Service
public class CourseCategoryService {

    private CourseCategoryRepository courseCategoryRepository;

    public CourseCategoryService(CourseCategoryRepository courseCategoryRepository) {
        this.courseCategoryRepository = courseCategoryRepository;
    }

     // 更具id查询课程分类
    public CourseCategory getCategory(Long id) {
        return courseCategoryRepository.findById(id).orElse(null);
    }

    // 添加课程分类
    public CourseCategory saveCategory(CourseCategory courseCategory) {
        return courseCategoryRepository.save(courseCategory);
    }

    // 删除课程分类
    public void deleteCategory(Long id) {
        courseCategoryRepository.deleteById(id);
    }

    // 获取所有课程分类
    public Iterable<CourseCategory> getAllCategories() {
        return courseCategoryRepository.findAll();
    }


}
