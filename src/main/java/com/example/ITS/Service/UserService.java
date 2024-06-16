package com.example.ITS.Service;

import com.example.ITS.Entity.Student;
import com.example.ITS.Entity.Teacher;
import com.example.ITS.Entity.User;
import com.example.ITS.Repository.StudentRepository;
import com.example.ITS.Repository.TeacherRepository;
import com.example.ITS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public User registerStudentUser(User user) {
        user = userRepository.save(user);
        // 创建一个新的学生实体
        Student student = new Student();
        // 设置学生实体的其他字段...
        student.setUser(user);
        student = studentRepository.save(student);

        // 将新创建的学生实体与用户实体关联
        user.setStudent(student);

        return userRepository.save(user);
    }

    public User registerTeacherUser(User user) {
        user = userRepository.save(user);
        // 创建一个新的教师实体
        Teacher teacher = new Teacher();
        // 设置教师实体的其他字段...
        teacher.setUser(user);
        teacher = teacherRepository.save(teacher);

        // 将新创建的教师实体与用户实体关联
        user.setTeacher(teacher);

        return userRepository.save(user);
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User login(User user) {
        User foundUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return foundUser;
    }
}
