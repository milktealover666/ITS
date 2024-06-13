package com.example.ITS.Service.impl;

import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.Student;
import com.example.ITS.Entity.Teacher;
import com.example.ITS.Mapper.TeacherMapper;
import com.example.ITS.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public int addCourse(Course course) {
        return teacherMapper.addCourse(course);
    }

    @Override
    public Teacher findTeacherById(String id) {
        return  teacherMapper.findTeacherById(id);
    }

    @Override
    public int updateSelfInfo(Teacher teacher) {
        return teacherMapper.updateSelfInfo(teacher);
    }

    @Override
    public List<Map<String,Object>> findAllCourseByTeacherId(String id) {
        return teacherMapper.findAllCourseByTeacherId(id);
    }

    @Override
    public List<Student> findStudents(String courseId, String teacherId) {
        return teacherMapper.findStudents(courseId,teacherId);
    }
}
