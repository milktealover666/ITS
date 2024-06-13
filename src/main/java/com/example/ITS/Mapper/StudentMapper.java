package com.example.ITS.Mapper;


import com.example.ITS.Entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {

    public Student findStudentById(String id);

    public int updateSelfInfo(Student student);

    List<Map<String,Object>> findAllCourse();

    List<Map<String,Object>> findChosenCourse(String studentId);

    int chooseCourse(String courseId,String teacherId,String studentId);

    int updateCourseNum(String courseId);
}
