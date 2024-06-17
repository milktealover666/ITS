package com.example.ITS.Service;

import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.Teacher;
import com.example.ITS.Entity.User;
import com.example.ITS.Repository.CourseRepository;
import com.example.ITS.Repository.CourseResourceRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseResourceService {

    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseResource> getAllCourseResources() {
        return courseResourceRepository.findAll();
    }

    public List<CourseResource> getCourseResourcesByCourseId(Long courseId) {
        return courseResourceRepository.findByCourseId(courseId);
    }

    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
    
    public CourseResource addCourseResource(Long courseId, String resourceName, String resourceUrl, HttpSession session) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Teacher teacher = ((User) session.getAttribute("user")).getTeacher();
    
        CourseResource courseResource = new CourseResource();
        courseResource.setResourceName(resourceName);
        courseResource.setResourceUrl(resourceUrl);
        courseResource.setCourse(course);
        courseResource.setTeacher(teacher);
    
        return courseResourceRepository.save(courseResource);
    }
    

    public CourseResource updateCourseResource(CourseResource courseResource) {
        return courseResourceRepository.save(courseResource);
    }

    public void deleteCourseResource(Long id) {
        courseResourceRepository.deleteById(id);
    }

    public List<CourseResource> searchCourseResources(String keyword) {
        return courseResourceRepository.findByKeyword(keyword);
    }
    
}
