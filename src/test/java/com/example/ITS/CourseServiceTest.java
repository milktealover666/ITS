package com.example.ITS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    public void testSaveCourse() {
        Course course = new Course();
        course.setTitle("Test Course");
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course savedCourse = courseService.saveCourse(course);
        assertEquals("Test Course", savedCourse.getTitle());

        verify(courseRepository, times(1)).save(course);
    }
}
