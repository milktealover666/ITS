package com.example.ITS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data
public class CourseResource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="courseId", nullable=false)
    private Course course;

    @ManyToOne
    @JoinColumn(name="teacherId", nullable=false)
    private Teacher teacher;

    private String resourceName;

    private String resourceUrl;

}