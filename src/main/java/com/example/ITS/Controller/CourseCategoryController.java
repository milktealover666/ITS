package com.example.ITS.Controller;

import com.example.ITS.Entity.CourseCategory;
import com.example.ITS.Service.CourseCategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    // 课程分类页面
    @GetMapping("/courseCategory")
    public String getAllCategories(Model model) {
        Iterable<CourseCategory> categories = courseCategoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("courseCategory", new CourseCategory());
        return "courseCategory";
    }

    // 根据id、名称查找课程分类
    @GetMapping("/courseCategory/search")
    public String searchCategory(@RequestParam("keyword") String keyword, Model model) {
        List<CourseCategory> searchedCourseCategories = courseCategoryService.searchCategories(keyword);
        model.addAttribute("searchedCourseCategories", searchedCourseCategories);
        Iterable<CourseCategory> categories = courseCategoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("courseCategory", new CourseCategory());
        return "courseCategory";
    }

    // 添加课程分类
    @PostMapping("/courseCategory/create")
    public String saveCategory(@ModelAttribute CourseCategory courseCategory) {
        courseCategoryService.saveCategory(courseCategory);
        return "redirect:/courseCategory";
    }

    // 删除课程分类
    @PostMapping("/courseCategory/delete")
    public String deleteCategory(@RequestParam Long id) {
        courseCategoryService.deleteCategory(id);
        return "redirect:/courseCategory";
    }

    // 更新课程分类
    @GetMapping("/courseCategory/update")
    public String updateCategory(@RequestParam Long id, Model model) {
        CourseCategory courseCategory = courseCategoryService.getCategory(id);
        model.addAttribute("courseCategory", courseCategory);
        return "updateCourseCategory";
    }

    @PostMapping("/courseCategory/update")
    public String saveUpdatedCategory(@ModelAttribute CourseCategory courseCategory) {
        courseCategoryService.saveCategory(courseCategory);
        return "redirect:/courseCategory";
    }


}
