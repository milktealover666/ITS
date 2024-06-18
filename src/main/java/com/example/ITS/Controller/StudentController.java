package com.example.ITS.Controller;

import com.example.ITS.Entity.User;
import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.Student;
import com.example.ITS.Entity.Teacher;
import com.example.ITS.Service.CourseResourceService;
import com.example.ITS.Service.CourseService;
import com.example.ITS.Service.StudentService;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;
    @Autowired
    CourseResourceService courseResourceService;

    // 个人信息视图
    @GetMapping("/studentinfo")
    public String teacherInfo(Model model, HttpSession session) {
        // 从session中获取当前登录的用户
        User user = (User) session.getAttribute("user");
        if (user != null && "student".equals(user.getType())) {
            // 更新学生的课程资源列表
            userService.updateUser(user);
            // 将更新后的用户和课程资源列表添加到模型中
            double percentage = studentService.calculateResourcePercentage();
            model.addAttribute("percentage", percentage);
            model.addAttribute("user", user);
            model.addAttribute("courseResources", user.getStudent().getCourseResources());
        }
        // 返回studentinfo视图

        return "studentinfo";
    }

    // 个人信息视图
    @PostMapping("/studentinfo")
    public String showStudentInfo(@RequestParam Long resourceId, HttpSession session, Model model) {
        // 从session中获取当前用户
        User user = (User) session.getAttribute("user");
        if (user != null && "student".equals(user.getType())) {
            // 从数据库中获取对应的课程资源
            CourseResource resource = courseResourceService.getResourceById(resourceId);
            // 将课程资源添加到学生的课程资源列表中
            user.getStudent().getCourseResources().add(resource);
            // 更新学生的课程资源列表
            userService.updateUser(user);
            // 将更新后的用户和课程资源列表添加到模型中
            double percentage = studentService.calculateResourcePercentage();
            model.addAttribute("percentage", percentage);
            model.addAttribute("user", user);
            model.addAttribute("courseResources", user.getStudent().getCourseResources());
        }
        // 返回studentinfo视图
        return "studentinfo";
    }


    // 修改个人信息
    @GetMapping("/student/update")
    public String update_self_page(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        Student student = user.getStudent();
        model.addAttribute("user", user);
        model.addAttribute("student", student);
        return "updatestudentinfo";
    }


    @PostMapping("/student/update")
    public String updateSelfInfo(@ModelAttribute Student updatedStudent, RedirectAttributes ra){
        // 从数据库中获取已经存在的 Student 实体
        Student existingStudent = studentService.findStudentById(updatedStudent.getId());

        // 更新 existingStudent 的属性
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setMajor(updatedStudent.getMajor());

        // 保存 existingStudent
        studentService.updateSelfInfo(existingStudent);

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

    //学生课程页面
    @GetMapping("/student/courses")
    public String viewCourses(Model model) {
        model.addAttribute("course", new Course());

        // 获取所有课程
        Iterable<Course> iterableCourses = courseService.findAllCourses();
        List<Course> courses = new ArrayList<>();
        iterableCourses.forEach(courses::add);

        // 将所有课程添加到模型中
        model.addAttribute("courses", courses);
        return "studentCourses";
    }

    // 学生课程资源界面
    @GetMapping("/student/courseResource/{id}")
    public String viewCourseResource(@PathVariable Long id, Model model) {
        List<CourseResource> resources = courseResourceService.getCourseResourcesByCourseId(id);
        model.addAttribute("resources", resources);
        model.addAttribute("resource", new CourseResource());
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "studentCourseResource";
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

    // 未学习资源展示
    @GetMapping("/student/unlearnedResources")
    public ModelAndView getUnlearnedResources(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        Long studentId = currentUser.getStudent().getId();

        List<CourseResource> allResources = studentService.findAllCourseResources();
        List<CourseResource> learnedResources = studentService.findChosenCourseResource(studentId);

        List<CourseResource> unlearnedResources = allResources.stream()
                .filter(resource -> !learnedResources.contains(resource))
                .collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView("unlearnedResources");
        modelAndView.addObject("unlearnedResources", unlearnedResources);

        return modelAndView;
    }

}
