package com.tayeb.chatbot;


import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class chat_controller {
    @Autowired
    agent_AI agent_AI;
    @GetMapping("chat")
    public String chat(String message) {
        return agent_AI.ask(new Prompt(message));
    }
}
