package com.example.ITS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ITS.CourseService;
import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.CourseCategory;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @PostMapping("/update")
    public String updateCourse(@ModelAttribute Course course) {
        courseService.updateCourse(course);
        return "redirect:/courses";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestParam Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    @PostMapping("/categories/create")
    public String createCategory(@ModelAttribute CourseCategory category) {
        courseService.saveCategory(category);
        return "redirect:/courses";
    }

    @PostMapping("/categories/delete")
    public String deleteCategory(@RequestParam Long id) {
        courseService.deleteCategory(id);
        return "redirect:/courses";
    }

    @GetMapping("/categories/{id}")
    public CourseCategory getCategory(@PathVariable Long id) {
        return courseService.getCategory(id);
    }

}
