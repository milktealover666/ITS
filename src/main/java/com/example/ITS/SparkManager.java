package com.example.ITS;

import com.example.ITS.SparkClient;
import com.example.ITS.constant.SparkApiVersion;
import com.example.ITS.Entity.SparkMessage;
import com.example.ITS.Entity.SparkSyncChatResponse;
import com.example.ITS.Entity.request.SparkRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SparkManager {
    @Resource
    private SparkClient sparkClient;

    public String QA(final String question){
        String precondition = "我是一名学生，请你回答我的问题：";
        List<SparkMessage> messages = new ArrayList<>();
        messages.add(SparkMessage.systemContent(precondition));
        messages.add(SparkMessage.userContent(question));
        SparkRequest sparkRequest = SparkRequest.builder()
                .messages(messages)
                .maxTokens(2048)
                .temperature(0.2)
                .apiVersion(SparkApiVersion.V3_5)
                .build();
        SparkSyncChatResponse chatResponse = sparkClient.chatSync(sparkRequest);
        String responseContent = chatResponse.getContent();
        log.info("接收到星火回答：{}",responseContent);
        return responseContent;
    }

    public String learnPath(final String courseName, final List<String> learned, final List<String> unlearned){
        // learned 为已学习过的资源
        // unlearned 为还未学习的资源
        String precondition = "我是一名学生，在" + courseName + "课程中我已经学习了" + learned + "还有" + unlearned + "未学习，请为我接下来的学习做一个指南";
        List<SparkMessage> messages = new ArrayList<>();
        messages.add(SparkMessage.userContent(precondition));
        SparkRequest sparkRequest = SparkRequest.builder()
                .messages(messages)
                .maxTokens(4096)
                .temperature(0.2)
                .apiVersion(SparkApiVersion.V3_5)
                .build();
        SparkSyncChatResponse chatResponse = sparkClient.chatSync(sparkRequest);
        String responseContent = chatResponse.getContent();
        log.info("接收到星火回答：{}",responseContent);
        return responseContent;
    }

    public String recommend(final List<String> learned){
        // learned 为已学习过的课程
        String precondition = "我是一名学生，我已经学习了" + learned + "课程，请为我接下来适合学习什么做一个指引";
        List<SparkMessage> messages = new ArrayList<>();
        messages.add(SparkMessage.userContent(precondition));
        SparkRequest sparkRequest = SparkRequest.builder()
                .messages(messages)
                .maxTokens(4096)
                .temperature(0.2)
                .apiVersion(SparkApiVersion.V3_5)
                .build();
        SparkSyncChatResponse chatResponse = sparkClient.chatSync(sparkRequest);
        String responseContent = chatResponse.getContent();
        log.info("接收到星火回答：{}",responseContent);
        return responseContent;
    }
}
