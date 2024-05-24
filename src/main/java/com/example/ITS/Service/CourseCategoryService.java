package com.example.ITS.Service;

import java.util.ArrayList;
import java.util.List;

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

    // 根据名称查询课程分类
    public List<CourseCategory> searchCategories(String keyword) {
    List<CourseCategory> categories = new ArrayList<>();
    try {
        // 尝试将关键词转换为长整型数字
        Long id = Long.parseLong(keyword);
        // 如果转换成功，那么按照ID进行查询
        CourseCategory category = courseCategoryRepository.findById(id).orElse(null);
        if (category != null) {
            categories.add(category);
        }
    } catch (NumberFormatException e) {
        // 如果转换失败，那么按照名称进行查询
        categories = courseCategoryRepository.findByNameContaining(keyword);
    }
    return categories;
}

}
