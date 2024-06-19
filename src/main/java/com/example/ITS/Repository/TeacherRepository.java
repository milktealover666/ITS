package com.example.ITS.Repository;

import com.example.ITS.Entity.Student;
import com.example.ITS.Entity.Teacher;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

    @Query("SELECT c FROM CourseResource c WHERE c.teacher.id = :id")
    List<Map<String,Object>> findAllCourseResourcesByTeacherId(@Param("id") long id);

    @Query("SELECT s FROM Student s JOIN s.courseResources cr WHERE cr.id = :courseResourceId AND cr.teacher.id = :teacherId")
    List<Student> findStudents(@Param("courseResourceId") long courseResourceId, @Param("teacherId") long teacherId);

}
