package com.example.ITS;

import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.Teacher;
import com.example.ITS.Service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    private Teacher teacher;
    private CourseResource courseResource;

    @BeforeEach
    public void setUp() {
        teacher = new Teacher();
        // Set teacher properties

        courseResource = new CourseResource();
        // Set courseResource properties
    }

    @Test
    public void testAddCourseResource() throws Exception {
        mockMvc.perform(post("/teacher/add_course_resource")
                .flashAttr("courseResource", courseResource))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testUpdateSelfInfo() throws Exception {
        mockMvc.perform(post("/teacher/update_selfInfo")
                .flashAttr("teacher", teacher))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testFindStudents() throws Exception {
        mockMvc.perform(get("/findStudents")
                .param("courseId", "1"))
                .andExpect(status().isOk());
    }
}
