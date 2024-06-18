package com.example.ITS.Controller;

import com.example.ITS.Entity.SparkMessage;
import com.example.ITS.SparkManager;
import com.example.ITS.Service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class QAController {

    @Autowired
    private SparkManager sparkManager;
    @Autowired
    StudentService studentService;


    private List<SparkMessage> history = new ArrayList<>();
    private List<String> conversation = new ArrayList<>();

    @GetMapping("/QA")
    public String qaPage() {
        return "QA";
    }
    
    @PostMapping("/getAnswer")
    public ResponseEntity<Map<String, List<String>>> getAnswer(@RequestBody Map<String, String> payload) {
        String question = payload.get("question");
        String answer = sparkManager.QA(history,question);
        history.add(SparkMessage.userContent(question));
        history.add(SparkMessage.assistantContent(answer));
        conversation.add("Me: " + question);
        conversation.add("SparkChat: " + answer);
        Map<String, List<String>> response = new HashMap<>();
        response.put("conversation", new ArrayList<>(conversation));
        return ResponseEntity.ok(response);
    }


}

