package com.example.ITS.Service;

import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Repository.CourseResourceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseResourceService {

    @Autowired
    private CourseResourceRepository courseResourceRepository;

    public List<CourseResource> getAllCourseResources() {
        return courseResourceRepository.findAll();
    }

    public List<CourseResource> getCourseResourcesByCourseId(Long courseId) {
        return courseResourceRepository.findByCourseId(courseId);
    }

    public CourseResource addCourseResource(CourseResource courseResource) {
        return courseResourceRepository.save(courseResource);
    }

    public CourseResource updateCourseResource(CourseResource courseResource) {
        return courseResourceRepository.save(courseResource);
    }

    public void deleteCourseResource(Long id) {
        courseResourceRepository.deleteById(id);
    }
}
