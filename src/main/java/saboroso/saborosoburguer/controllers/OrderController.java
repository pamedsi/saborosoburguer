package saboroso.saborosoburguer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTO.order.postOrder.OrderForPostDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.Message;
import saboroso.saborosoburguer.services.OrderService;

@RestController
public class OrderController extends BaseController {
    private final OrderService orderService;
    private final SimpMessagingTemplate template;


    public OrderController(OrderService orderService, SimpMessagingTemplate template) {
        this.template = template;
        this.orderService = orderService;
    }
    @PostMapping(value = "/make-order")
    public ResponseEntity<?> requestOrder(@RequestBody OrderForPostDTO orderForPostDTO) {
        orderService.makeOrder(orderForPostDTO);
        template.convertAndSend("/topic/orders", orderForPostDTO);
        return ResponseEntity.ok(new Message("Pedido feito!", null));
    }
    @GetMapping(value = "/order-manager")
    public ResponseEntity<?> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
