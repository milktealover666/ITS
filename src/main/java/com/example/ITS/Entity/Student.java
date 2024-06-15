package com.example.ITS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

import java.util.List;

@Entity
@Data
public class Student {

    @Id
    private Long id;
    private String name;
    private String sex;
    private String classname;
    private String major;

    @ManyToMany
    @JoinTable(
        name = "student_course_resources",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_resource_id")
    )
    private List<CourseResource> courseResources;
}
