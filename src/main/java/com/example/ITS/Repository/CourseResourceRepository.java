package com.example.ITS.Repository;

import com.example.ITS.Entity.CourseResource;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CourseResourceRepository extends JpaRepository<CourseResource, Long> {
}