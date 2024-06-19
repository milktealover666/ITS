package com.example.ITS.Controller;

import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Service.CourseResourceService;
import com.example.ITS.Service.CourseService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CourseResourceController {

    @Autowired
    private CourseResourceService courseResourceService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/courseResource/{id}")
    public String viewCourseResource(@PathVariable Long id, Model model) {
        List<CourseResource> resources = courseResourceService.getCourseResourcesByCourseId(id);
        model.addAttribute("resources", resources);
        model.addAttribute("resource", new CourseResource());
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "courseResource";
    }

    // 添加课程资源
    @PostMapping("/courseResource/add")
    public String addCourseResource(@RequestParam("courseId") Long courseId, @ModelAttribute("courseResource") CourseResource courseResource, HttpSession session) {
        Course course = courseService.getCourseById(courseId);
        courseResource.setCourse(course);
        String resourceName = courseResource.getResourceName();
        String resourceUrl = courseResource.getResourceUrl();
        courseResourceService.addCourseResource(courseId, resourceName, resourceUrl, session);
        return "redirect:/courseResource/search";
    }

    // 查找课程资源
    @GetMapping("/courseResource/search")
    public ModelAndView search(@RequestParam(required = false) String keyword) {
        List<CourseResource> resources = courseResourceService.searchCourseResources(keyword);
        ModelAndView mav = new ModelAndView("courseResource");
        mav.addObject("resources", resources);
        return mav;
    }

}
