package com.example.ITS.Controller;

import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Service.CourseResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CourseResourceController {

    @Autowired
    private CourseResourceService courseResourceService;

    @GetMapping("/courseResource/{id}")
    public String viewCourseResource(@PathVariable Long id, Model model) {
        List<CourseResource> resources = courseResourceService.getCourseResourcesByCourseId(id);
        model.addAttribute("resources", resources);
        model.addAttribute("resource", new CourseResource());
        return "courseResource";
    }
}
