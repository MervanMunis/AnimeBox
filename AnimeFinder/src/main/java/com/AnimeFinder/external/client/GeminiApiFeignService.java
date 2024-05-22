package com.AnimeFinder.external.client;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "geminiApi", url = "http://localhost:5000")  // Use a meaningful name and specify the URL
public interface GeminiApiFeignService {

    @PostMapping("/get_response")  // Match the endpoint in your Python Flask app
    String getResponse(@RequestBody Map<String, Object> userInput);
}
