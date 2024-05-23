package com.example.ITS.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ITS.Service.CourseCategoryService;
import com.example.ITS.Service.CourseService;
import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.CourseCategory;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
    private CourseCategoryService courseCategoryService;

    public CourseController(CourseService courseService,CourseCategoryService courseCategoryService) {
        this.courseService = courseService;
        this.courseCategoryService = courseCategoryService;
    }

    // 课程页面
    @GetMapping("/courses")
    public String viewCourses(Model model) {
        model.addAttribute("course", new Course());
        Iterable<CourseCategory> categories = courseCategoryService.getAllCategories();
        model.addAttribute("categories", categories);

        // 获取所有课程
        Iterable<Course> iterableCourses = courseService.findAllCourses();
        List<Course> courses = new ArrayList<>();
        iterableCourses.forEach(courses::add);

        // 将所有课程添加到模型中
        model.addAttribute("courses", courses);
        return "courses";
    }

    // 添加课程
    @PostMapping("/courses/create")
    public String createCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    // 更新课程
    @GetMapping("/courses/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Course course = courseService.getCourseById(id);
        Iterable<CourseCategory> categories = courseCategoryService.getAllCategories(); // 获取所有类别
        model.addAttribute("course", course);
        model.addAttribute("categories", categories); // 将类别列表添加到模型中
        return "updateCourse";
    }

    @PostMapping("/courses/update")
    public String updateCourse(@ModelAttribute Course course) {
        courseService.updateCourse(course);
        return "redirect:/courses";
    }

    // 删除课程
    @PostMapping("/courses/delete")
    public String deleteCourse(@RequestParam Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    // 根据id查找课程
    @GetMapping("/courses/search")
    public String searchCourse(@RequestParam("id") Long id, Model model) {
        Course searchedCourse = courseService.getCourseById(id);
        model.addAttribute("searchedCourse", searchedCourse);
        Iterable<CourseCategory> categories = courseCategoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("course", new Course());

        // 获取所有课程
        Iterable<Course> iterableCourses = courseService.findAllCourses();
        List<Course> courses = new ArrayList<>();
        iterableCourses.forEach(courses::add);

        // 将所有课程添加到模型中
        model.addAttribute("courses", courses);
        return "courses";
    }

}
