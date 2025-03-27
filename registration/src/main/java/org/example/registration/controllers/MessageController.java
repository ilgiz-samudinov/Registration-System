//package org.example.registration.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.example.registration.entities.Message;
//import org.example.registration.services.MessageService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/messages")
//@RequiredArgsConstructor
//@CrossOrigin(origins ="*")
//public class MessageController {
//    private final MessageService service;
//
//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody Message message){
//        service.save(message);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
