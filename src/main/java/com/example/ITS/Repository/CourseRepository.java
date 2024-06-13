package com.example.ITS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ITS.Entity.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    
    @Query("SELECT c FROM Course c WHERE c.title LIKE %:keyword% OR c.category.name LIKE %:keyword%")
    List<Course> search(@Param("keyword") String keyword);

}
