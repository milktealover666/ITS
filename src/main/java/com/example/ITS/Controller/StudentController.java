package com.example.ITS.Controller;

import com.example.ITS.Entity.User;
import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.Student;
import com.example.ITS.Entity.Teacher;
import com.example.ITS.Service.StudentService;
import com.example.ITS.Service.TeacherService;

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


import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    // 修改个人信息
    @GetMapping("/student/update/{id}")
    public String update_self_page(@PathVariable Long id, Model model){
        Student student = studentService.findStudentById(id);
        model.addAttribute("student",student);
        return "updatestudentinfo";
    }


    @PostMapping("/updatestudentinfo")
    public String updateSelfInfo(@ModelAttribute Student student, RedirectAttributes ra){
        studentService.updateSelfInfo(student);
        ra.addFlashAttribute("message","修改成功");
        return "redirect:/studentinfo";
    }

    //浏览选课学生信息
    @GetMapping("/all_course_info_page")
    public String course_info_page(HttpSession session, Model model){

        List<CourseResource> courseResources = studentService.findAllCourseResources();
        if (courseResources.size() == 0){
            model.addAttribute("message","暂无课程资源信息");
            return "main";
        }
        model.addAttribute("courseResources",courseResources);
        return "student/allCourseResourcesInfoPage";
    }

    // 查看老师信息
    @GetMapping("/findTeacher")
    public String findTeacher(@RequestParam("teacherId") long teacherId,Model model){
        Teacher teacher = teacherService.findTeacherById(teacherId);
        model.addAttribute("teacher",teacher);
        return "student/teacherInfoPage";
    }

    // 获得所有可以选课的课程信息
    @GetMapping("/all_chosen_course_info_page")
    public String all_chosen_course_info_page(Model model,HttpSession session){
        Long studentId = Long.parseLong(((User)session.getAttribute("user")).getUsername());
        List<CourseResource> courses = studentService.findChosenCourseResource(studentId);
        if (courses.size() == 0){
            model.addAttribute("message","暂无课程信息");
            return "main";
        }
        model.addAttribute("courses",courses);
        return "student/allChosenCourseInfoPage";
    }

    // 选择课程资源
    @PostMapping("/choose_course_resource")
    public String choose_course_resource(Model model, HttpSession session,
                                         @RequestParam("resourceId") long resourceId,
                                         RedirectAttributes ra){
        User currentUser = (User) session.getAttribute("user");
        Long studentId = currentUser.getStudent().getId();                                            
        boolean isSuccess = studentService.chooseCourseResource(studentId,resourceId);
        if (isSuccess){
            ra.addAttribute("message","选课资源成功！");
        }else {
            ra.addAttribute("message","选课资源失败！");
        }
        return "redirect:/courseResource/search";
    }
}
