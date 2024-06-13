package com.example.ITS.Service.impl;

import com.example.ITS.Entity.Student;
import com.example.ITS.Mapper.StudentMapper;
import com.example.ITS.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public Student findStudentById(String id) {
        return studentMapper.findStudentById(id);
    }

    @Override
    public int updateSelfInfo(Student student) {
        return studentMapper.updateSelfInfo(student);
    }

    @Override
    public List<Map<String,Object>> findAllCourse() {
        return studentMapper.findAllCourse();
    }

    @Override
    public List<Map<String,Object>> findChosenCourse(String studentId) {
        return studentMapper.findChosenCourse(studentId);
    }

    @Override
    public int chooseCourse(String courseId, String teacherId, String studentId) {
        return studentMapper.chooseCourse(courseId,teacherId,studentId);
    }

    @Override
    public int updateCourseNum(String courseId) {
        return studentMapper.updateCourseNum(courseId);
    }


}
