package com.example.ITS.Controller;

import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.Student;
import com.example.ITS.Entity.Teacher;
import com.example.ITS.Entity.User;
import com.example.ITS.Service.CourseResourceService;
import com.example.ITS.Service.CourseService;
import com.example.ITS.Service.TeacherService;
import com.example.ITS.Service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;
    @Autowired
    CourseResourceService courseResourceService;

    @GetMapping("/teacherinfo")
    public String teacherInfo(Model model, HttpSession session) {
        // 从session中获取当前登录的用户
        User user = (User) session.getAttribute("user");

        // 检查用户是否是教师
        if (user != null && user.getType().equals("teacher")) {
            Teacher teacher = user.getTeacher();

            // 获取教师的课程资源
            // 这里假设你有一个CourseResourceService来获取数据
            List<CourseResource> courseResources = courseResourceService.getCourseResourcesByTeacherid(teacher);

            // 将数据添加到模型中
            model.addAttribute("user", user);
            model.addAttribute("courseResources", courseResources);
        }

        // 返回teacherinfo视图
        return "teacherinfo";
    }


    // 教师课程资源界面
    @GetMapping("/teacher/courseResource/{id}")
    public String viewCourseResource(@PathVariable Long id, Model model) {
        List<CourseResource> resources = courseResourceService.getCourseResourcesByCourseId(id);
        model.addAttribute("resources", resources);
        model.addAttribute("resource", new CourseResource());
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "teacherCourseResource";
    }

    @PostMapping("/teacher/courseResource/add")
    public String addCourseResource(@RequestParam("courseId") Long courseId, @ModelAttribute("courseResource") CourseResource courseResource, HttpSession session) {
        Course course = courseService.getCourseById(courseId);
        courseResource.setCourse(course);
        String resourceName = courseResource.getResourceName();
        String resourceUrl = courseResource.getResourceUrl();
        courseResourceService.addCourseResource(courseId, resourceName, resourceUrl, session);
        return "redirect:/teacherinfo";
    }

    // 添加课程资源信息
    @GetMapping("/add_course_resource_page")
    public String add_course_resource_page(){
        return "teacher/addCourseResourcePage";
    }

    @PostMapping("/teacher/add_course_resource")
    public String addCourseResource(CourseResource courseResource, RedirectAttributes ra){
        teacherService.addCourseResource(courseResource);
        ra.addAttribute("message","添加课程资源成功");
        return "redirect:/add_course_resource_page";
    }

    // 修改个人信息
    @GetMapping("/update_self_page")
    public String update_self_page(javax.servlet.http.HttpSession session, Model model){
        Long id = Long.parseLong(((User)session.getAttribute("user")).getUsername());
        Teacher teacher = teacherService.findTeacherById(id);
        model.addAttribute("teacher",teacher);
        return "teacher/updateSelfInfoPage";
    }

    @PostMapping("/teacher/update_selfInfo")
    public String updateSelfInfo(@ModelAttribute Teacher teacher, RedirectAttributes ra){
        teacherService.updateSelfInfo(teacher);
        ra.addFlashAttribute("message","修改成功");
        return "redirect:/update_self_page";
    }

    //教师课程页面
    @GetMapping("/teacher/courses")
    public String viewCourses(Model model) {
        model.addAttribute("course", new Course());

        // 获取所有课程
        Iterable<Course> iterableCourses = courseService.findAllCourses();
        List<Course> courses = new ArrayList<>();
        iterableCourses.forEach(courses::add);

        // 将所有课程添加到模型中
        model.addAttribute("courses", courses);
        return "teacherCourses";
    }


    //浏览选课学生信息
    @GetMapping("/course_info_page")
    public String course_info_page(HttpSession session, Model model){
        Long id = Long.parseLong(((User)session.getAttribute("user")).getUsername());
        List<Map<String,Object>> courses = teacherService.findAllCourseResourcesByTeacherId(id);
        if (courses.size() == 0){
            model.addAttribute("message","暂无课程信息");
            return "main";
        }
        model.addAttribute("courses",courses);
        return "teacher/courseInfoPage";
    }

    @GetMapping("/findStudents")
    public String findStudents(@RequestParam("courseId") long courseId,HttpSession session,Model model){
        Long teacherId = Long.parseLong(((User)session.getAttribute("user")).getUsername());
        List<Student> students = teacherService.findStudents(courseId,teacherId);
        if (students.size() == 0){
            model.addAttribute("message","暂无学生选本文课");
            return "main";
        }
        model.addAttribute("students",students);
        return "teacher/studentInfoPage";
    }
}
