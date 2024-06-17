package com.example.ITS.Repository;

import com.example.ITS.Entity.CourseResource;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CourseResourceRepository extends JpaRepository<CourseResource, Long> {
    List<CourseResource> findByCourseId(Long courseId);

    @Query("SELECT cr FROM CourseResource cr WHERE cr.resourceName LIKE %:keyword% OR cr.teacher.name LIKE %:keyword%")
    List<CourseResource> findByKeyword(@Param("keyword") String keyword);

}