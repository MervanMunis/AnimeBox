package com.OllamaAi.OllamaAi.service;

import lombok.AllArgsConstructor;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OllamaAiServiceImpl implements IOllamaAiService{
    
    private final OllamaChatClient Client;

    @Override
    public String createChat(String message) {
        String response = Client.call(message);
        return response;
    }
}
