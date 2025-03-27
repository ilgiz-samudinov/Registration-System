//package org.example.registration.services;
//
//import lombok.RequiredArgsConstructor;
//import org.example.registration.entities.Message;
//import org.example.registration.events.MessageEvent;
//import org.example.registration.exceptions.NotFoundException;
//import org.example.registration.repositories.MessageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MessageService {
//    private final MessageRepository repository;
//    @Autowired
//    private ApplicationEventPublisher eventPublisher;
//
//    public List<Message>  getAll(){
//        return repository.findAll();
//    }
//
////    public void save(Message message){
////        repository.save(message);
////        eventPublisher.publishEvent(new MessageEvent(this, message));
////
////    }
//
//    public Message update(Long id, Message message){
//        Message found = repository.findById(id)
//                .orElseThrow(()-> new NotFoundException("about is not found"));
//
//        found.setMessage(message.getMessage());
//        return repository.save(found);
//    }
//
//    public void delete(Long id){
//        repository.deleteById(id);
//    }
//
//}
