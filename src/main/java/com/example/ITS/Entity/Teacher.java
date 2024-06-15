package com.example.ITS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Teacher {
    @Id
    private Long id;
    private String name;
    private String sex;
    private String academy;
    private String title;
}
