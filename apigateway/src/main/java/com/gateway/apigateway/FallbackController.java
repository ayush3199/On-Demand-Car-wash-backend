package com.gateway.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("CustomerServiceFallback")
    public String CustomerServiceFallback() {
        return "Customer Service Is Down At This Time";
    }

    @GetMapping("/washerServiceFallback")
    public String washerServiceFallback() {
        return "washer Service Is Down At This Time";
    }

    @GetMapping("/carServiceFallback")
    public String carServiceFallback() {
        return "Car Service Is Down At This Time";
    }

}
