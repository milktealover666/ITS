package com.example.ITS.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ITS.Entity.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
