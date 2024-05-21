package com.example.ITS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ITS.CourseService;
import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.CourseCategory;

@Controller
public class ViewController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String viewCourses(Model model) {
        model.addAttribute("course", new Course());
        Iterable<CourseCategory> categories = courseService.getAllCategories();
        model.addAttribute("categories", categories);
        return "courses";
    }
}
