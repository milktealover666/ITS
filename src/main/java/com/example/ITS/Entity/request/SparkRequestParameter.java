package com.example.ITS.Entity.request;

import com.example.ITS.Entity.SparkChatParameter;

import java.io.Serializable;

public class SparkRequestParameter implements Serializable {
    private static final long serialVersionUID = 4502096141480336425L;

    private SparkChatParameter chat;

    public SparkRequestParameter() {
    }

    public SparkRequestParameter(SparkChatParameter chat) {
        this.chat = chat;
    }

    public SparkChatParameter getChat() {
        return chat;
    }

    public void setChat(SparkChatParameter chat) {
        this.chat = chat;
    }
}
