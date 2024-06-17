package com.example.ITS.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
public class SparkChatParameter implements Serializable {
    private static final long serialVersionUID = -1815416415486571475L;

    private String domain = "generalv2";

    private Double temperature;

    @JsonProperty("max_tokens")
    private Integer maxTokens;

    @JsonProperty("top_k")
    private Integer topK;

    @JsonProperty("chat_id")
    private String chatId;

    public static SparkChatParameter defaultParameter() {
        return new SparkChatParameter();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Integer getTopK() {
        return topK;
    }

    public void setTopK(Integer topK) {
        this.topK = topK;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
