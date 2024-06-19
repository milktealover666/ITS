package com.example.ITS.Controller;

import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.SparkMessage;
import com.example.ITS.Entity.User;
import com.example.ITS.SparkManager;
import com.example.ITS.Service.StudentService;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
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
    @Autowired
    HttpSession httpSession;


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
        
        // 创建 Markdown 解析器和 HTML 渲染器
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // 将答案从 Markdown 格式转换为 HTML 格式
        com.vladsch.flexmark.util.ast.Node document = parser.parse(answer);
        answer = renderer.render(document);

        history.add(SparkMessage.userContent(question));
        history.add(SparkMessage.assistantContent(answer));
        conversation.add("Me: " + question);
        conversation.add("SparkChat: " + answer);
        Map<String, List<String>> response = new HashMap<>();
        response.put("conversation", new ArrayList<>(conversation));
        return ResponseEntity.ok(response);
    }

    // 智能课程推荐
    @GetMapping("/recommendCourses")
    public String recommendCourses(Model model) {

        User user = (User) httpSession.getAttribute("user");
        Long userId = user.getStudent().getId();

        List<CourseResource> learned = studentService.findChosenCourseResource(userId);
        List<CourseResource> allResources = studentService.findAllCourseResources();
        allResources.removeAll(learned);
        List<String> unlearned = allResources.stream().map(CourseResource::getResourceName).collect(Collectors.toList());
        String courseName = learned.get(0).getCourse().getTitle();
        // 调用 learnPath 方法，传入课程名称，已学过的资源和未学过的资源
        String recommendation = sparkManager.learnPath(courseName, learned.stream().map(CourseResource::getResourceName).collect(Collectors.toList()), unlearned);

        // 创建 Markdown 解析器和 HTML 渲染器
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // 将推荐结果从 Markdown 格式转换为 HTML 格式
        com.vladsch.flexmark.util.ast.Node document = parser.parse(recommendation);
        String htmlRecommendation = renderer.render(document);

        // 将 HTML 格式的推荐结果添加到模型中
        model.addAttribute("recommendation", htmlRecommendation);
        return "recommendation";
    }


}

