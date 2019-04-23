package com.akatkar.learning.redis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DemoController {

    @Autowired
    private StringRedisTemplate redis;

    @GetMapping("/")
    String getIndex(Model model){
        return "index";
    }

    @PostMapping(params = "load")
    String loadMethod(HttpServletRequest request, Model model){
        // Get key value from request
        String key = request.getParameter("key");
        // Access to value from cache
        HashOperations<String, String, String> hash = redis.opsForHash();
        String value = hash.get("cache",key);
        // Send it back
        model.addAttribute("key",key);
        model.addAttribute("value", value);
        return "index";
    }

    @PostMapping(params = "save")
    String saveMethod(HttpServletRequest request, Model model){
        // Get key and value pair from request
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        // cache them on Redis
        HashOperations<String, String, String> hash = redis.opsForHash();
        hash.put("cache",key, value);
        return "index";
    }
}
