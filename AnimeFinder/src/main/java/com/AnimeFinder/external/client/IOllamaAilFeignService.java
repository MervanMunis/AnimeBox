package com.AnimeFinder.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("OLLAMA-AI/ollamaaiApi")
public interface IOllamaAilFeignService {
    
    @PostMapping
    String createChat(@RequestBody String message);
    
}
