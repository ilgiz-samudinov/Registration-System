package org.example.registration.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class TestController {

    @GetMapping("/public/test")
    public ResponseEntity<String> publicEndpoint(){
        return ResponseEntity.ok("Это публичный метод");
    }

    @GetMapping("/test")
    public ResponseEntity<String> authenticatedEndpoint(){
        return ResponseEntity.ok("Вы успешно аутентифицированы!");
    }

    @GetMapping("/admin/test")
    public ResponseEntity<String> adminEndpoint(){
        return ResponseEntity.ok("Вы админстратор");
    }


}
