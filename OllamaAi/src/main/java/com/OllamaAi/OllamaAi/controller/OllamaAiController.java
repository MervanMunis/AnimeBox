package com.OllamaAi.OllamaAi.controller;

import com.OllamaAi.OllamaAi.service.IOllamaAiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("ollamaaiApi")
public class OllamaAiController {
   
    private final IOllamaAiService OllamaAiService;
    
    @PostMapping
    public ResponseEntity<String> createChat(@RequestBody String message) {
        String response = OllamaAiService.createChat(message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
}
