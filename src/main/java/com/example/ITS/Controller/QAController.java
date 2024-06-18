package com.example.ITS.Controller;

import com.example.ITS.SparkManager;
import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class QAController {

    @Autowired
    private SparkManager sparkManager;
    @Autowired
    StudentService studentService;

    @GetMapping("/QA")
    public String qaPage() {
        return "QA";
    }
    
    @PostMapping("/getAnswer")
    public ResponseEntity<Map<String, String>> getAnswer(@RequestBody Map<String, String> payload) {
        String question = payload.get("question");
        String answer = sparkManager.QA(question);
        Map<String, String> response = new HashMap<>();
        response.put("answer", answer);
        return ResponseEntity.ok(response);
    }

}

