package com.example.ITS.Repository;

import org.springframework.stereotype.Repository;

import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    @Query("SELECT c FROM CourseResource c")
    List<CourseResource> findAllCourseResources();

    @Query("SELECT s.courseResources FROM Student s WHERE s.id = :studentId")
    List<CourseResource> findChosenCourseResource(@Param("studentId") long studentId);

}
