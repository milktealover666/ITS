package com.example.ITS.Service;

import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.Student;
import com.example.ITS.Repository.CourseResourceRepository;
import com.example.ITS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private CourseResourceRepository courseResourceRepository;

    public Student findStudentById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateSelfInfo(Student student) {
        return studentRepository.save(student);
    }

    public List<CourseResource> findAllCourseResources() {
        return studentRepository.findAllCourseResources();
    }

    public List<CourseResource> findChosenCourseResource(long studentId) {
        return studentRepository.findChosenCourseResource(studentId);
    }    

    public boolean chooseCourseResource(long resourceId, long studentId) {
        try {
            Student student = studentRepository.findById(studentId).orElse(null);
            CourseResource resource = courseResourceRepository.findById(resourceId).orElse(null);

            if (student != null && resource != null) {
                student.getCourseResources().add(resource);
                studentRepository.save(student);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // handle exception
            return false;
        }
    }


}
