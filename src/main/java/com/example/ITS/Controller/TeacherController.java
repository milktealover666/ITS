package com.example.ITS.Controller;

import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.Student;
import com.example.ITS.Entity.Teacher;
import com.example.ITS.Entity.User;
import com.example.ITS.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/teacherinfo")
    public String showTeacherInfo(Model model,Principal principal) {
        // 获取当前登录的教师信息
        Teacher currentTeacher = teacherService.findTeacherById(Long.parseLong(principal.getName()));
        model.addAttribute("teacher", currentTeacher);
        return "teacherinfo";  // 返回视图名称
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
