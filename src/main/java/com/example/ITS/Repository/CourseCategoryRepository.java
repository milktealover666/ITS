package com.example.ITS.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ITS.Entity.CourseCategory;

@Repository
public interface CourseCategoryRepository extends CrudRepository<CourseCategory, Long> {
}
