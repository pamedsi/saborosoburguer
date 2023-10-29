package saboroso.saborosoburguer.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import saboroso.saborosoburguer.models.Message;

@Service
public class WebSocketHandlerService implements WebSocketHandler {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketHandlerService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Connection established on session: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        System.out.println("Connection closed on session: " + session.getId() + "with status: " + closeStatus.getCode());

    }
    public void alertNewOrder() {
        messagingTemplate.convertAndSend("/topic/orders", new Message("Novo pedido!", null));
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("Exception occurred:" +  exception.getMessage() + "on session: " + session.getId());
    }
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageText = (String) message.getPayload();
        System.out.println("Message: " + messageText);
        session.sendMessage(new TextMessage("Started processing: " + session + " - " + messageText));
        Thread.sleep(1000);
        session.sendMessage(new TextMessage("Completed processing: " + messageText));
        session.sendMessage(new TextMessage("Pedido!"));
    }
}
