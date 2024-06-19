package com.example.ITS.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ITS.Entity.CourseCategory;

@Repository
public interface CourseCategoryRepository extends CrudRepository<CourseCategory, Long> {

    List<CourseCategory> findByNameContaining(String name);

}
