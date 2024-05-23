package com.example.ITS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.ITS.Entity.Course;
import com.example.ITS.Repository.CourseCategoryRepository;
import com.example.ITS.Repository.CourseRepository;
import com.example.ITS.Service.CourseService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;
    @MockBean
    private CourseCategoryRepository courseCategoryRepository;

    // @Test
    // public void testSaveCourse() {
    //     Course course = new Course();
    //     course.setTitle("Test Course");
    //     course.setDescription("This is a test course.");

    //     courseService.saveCourse(course);

    //     verify(courseRepository).save(course);
    // }

    // @Test
    // public void testDeleteCourse() {
    //     Long id = 1L;

    //     courseService.deleteCourse(id);

    //     verify(courseRepository).deleteById(id);
    // }

    @Test
    public void testGetCourseById() {
        // 创建一个课程对象
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");

        // 当调用 courseRepository.findById(1L) 时，返回一个包含 course 的 Optional
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // 测试 courseService.getCourseById(1L)
        Course result = courseService.getCourseById(1L);

        // 验证结果
        assertEquals(course, result, "The course should match the expected course");

        // 当调用 courseRepository.findById(2L) 时，返回一个空的 Optional
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());

        // 测试 courseService.getCourseById(2L)
        result = courseService.getCourseById(2L);

        // 验证结果
        assertNull(result, "The result should be null as there is no course with id 2");
    }

    // @Test
    // public void testSaveCategory() {
    //     CourseCategory category = new CourseCategory();
    //     category.setName("Test Category");

    //     courseService.saveCategory(category);

    //     verify(courseCategoryRepository).save(category);
    // }

    // @Test
    // public void testDeleteCategory() {
    //     Long id = 1L;

    //     courseService.deleteCategory(id);

    //     verify(courseCategoryRepository).deleteById(id);
    // }
}
