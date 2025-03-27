//package org.example.registration.configs;
//
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.io.IOException;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class WebSocketHandler extends TextWebSocketHandler {
//
//    private static final ConcurrentHashMap<String, WebSocketSession>  sessions = new ConcurrentHashMap<>();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//       if(session.getPrincipal() != null){
//           String username  = session.getPrincipal().getName();
//           session.getAttributes().put("username", username);
//           sessions.put(username, session);
//           System.out.println("Пользователь " + username + " подключился.");
//       }
//       else{
//           System.out.println("Неаутентифицированный пользователь попытался подключиться.");
//           session.close();
//       }
//
//    }
//
//    private String getUserNameFromSession(WebSocketSession session) {
//        return (String) session.getAttributes().get("name");
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        System.err.println("Ошибка при передачи данных" + exception.getMessage());
//        exception.printStackTrace();
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        sessions.remove(session);
//        sendToAuthenticatedUsers("Клиент сессии " + session.getId() + "отключился");
//    }
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//
//    public static void sendToAuthenticatedUsers(String message) {
//        sessions.values().forEach(session -> {
//            try {
//                session.sendMessage(new TextMessage(message));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//}
