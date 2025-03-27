//package org.example.registration.events;
//
//import jakarta.persistence.PostPersist;
//import lombok.RequiredArgsConstructor;
//import org.example.registration.configs.WebSocketConfig;
//import org.example.registration.configs.WebSocketHandler;
//import org.example.registration.entities.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class MessageEventListener {
//    private final WebSocketHandler webSocketHandler;
//
//    @EventListener
//    public void onMessageCreated(MessageEvent event){
////        Message message  = event.getMessage();
////        String m = "New message added: " + message.getMessage();
//        String m = event.getMessage().getMessage();
//        webSocketHandler.sendToAuthenticatedUsers(m);
//    }
//}
