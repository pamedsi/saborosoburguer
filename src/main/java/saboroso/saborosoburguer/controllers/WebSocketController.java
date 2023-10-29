package saboroso.saborosoburguer.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import saboroso.saborosoburguer.models.Message;

@Controller
public class WebSocketController {

    @MessageMapping ("/hello")
    @SendTo ("/topic/greetings")
    public Message greeting(Message message) {
        return new Message("VEIO DO BACK ISSO AQUI ÓO, " + message.message(), null);
    }
}
