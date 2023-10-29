package saboroso.saborosoburguer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import saboroso.saborosoburguer.services.WebSocketHandlerService;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/orders").setAllowedOriginPatterns("**");
        registry.addEndpoint("/orders").setAllowedOriginPatterns("**").withSockJS();
    }


}
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
//        registry.addHandler(WebSocketHandlerService(), "/orders")
//                .setAllowedOrigins("*");
//    }
//
//    @Bean
//    WebSocketHandler WebSocketHandlerService() {
//        return new WebSocketHandlerService();
//    }
