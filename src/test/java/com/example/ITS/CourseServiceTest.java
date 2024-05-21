package com.example.ITS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.ITS.Entity.Course;
import com.example.ITS.Entity.CourseCategory;
import com.example.ITS.Repository.CourseCategoryRepository;
import com.example.ITS.Repository.CourseRepository;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;
    @MockBean
    private CourseCategoryRepository courseCategoryRepository;

    @Test
    public void testSaveCourse() {
        Course course = new Course();
        course.setTitle("Test Course");
        course.setDescription("This is a test course.");

        courseService.saveCourse(course);

        verify(courseRepository).save(course);
    }

    @Test
    public void testDeleteCourse() {
        Long id = 1L;

        courseService.deleteCourse(id);

        verify(courseRepository).deleteById(id);
    }

    @Test
    public void testSaveCategory() {
        CourseCategory category = new CourseCategory();
        category.setName("Test Category");

        courseService.saveCategory(category);

        verify(courseCategoryRepository).save(category);
    }

    @Test
    public void testDeleteCategory() {
        Long id = 1L;

        courseService.deleteCategory(id);

        verify(courseCategoryRepository).deleteById(id);
    }
}
