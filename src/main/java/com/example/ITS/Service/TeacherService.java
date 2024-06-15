package com.example.ITS.Service;

import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.Student;
import com.example.ITS.Entity.Teacher;
import com.example.ITS.Repository.CourseResourceRepository;
import com.example.ITS.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    private CourseResourceRepository courseResourceRepository;

    public CourseResource addCourseResource(CourseResource courseResource) {
        return courseResourceRepository.save(courseResource);
    }

    public Teacher findTeacherById(long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public Teacher updateSelfInfo(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Map<String,Object>> findAllCourseResourcesByTeacherId(long id) {
        return teacherRepository.findAllCourseResourcesByTeacherId(id);
    }

    public List<Student> findStudents(long courseId, long teacherId) {
        return teacherRepository.findStudents(courseId, teacherId);
    }
}
