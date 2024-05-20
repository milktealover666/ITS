package com.example.ITS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

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


}
