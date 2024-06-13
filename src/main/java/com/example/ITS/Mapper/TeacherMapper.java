package com.example.ITS.Mapper;


import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.Student;
import com.example.ITS.Entity.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {

    public int addCourse(Course course);

    public Teacher findTeacherById(String id);

    public int updateSelfInfo(Teacher teacher);

    public List<Map<String,Object>> findAllCourseByTeacherId(String id);

    public List<Student> findStudents(String courseId, String teacherId);

}
