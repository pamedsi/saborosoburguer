package saboroso.saborosoburguer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTO.order.postOrder.OrderForPostDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.Message;
import saboroso.saborosoburguer.services.OrderService;
import saboroso.saborosoburguer.services.WebSocketHandlerService;

@RestController
public class OrderController extends BaseController {
    private final OrderService orderService;
    private final WebSocketHandlerService webSocketHandlerService;


    public OrderController(OrderService orderService, WebSocketHandlerService webSocketHandlerService) {
        this.webSocketHandlerService = webSocketHandlerService;
        this.orderService = orderService;
    }
    @PostMapping("/make-order")
    public ResponseEntity<?> requestOrder(@RequestBody OrderForPostDTO orderForPostDTO) {
        orderService.makeOrder(orderForPostDTO);
        webSocketHandlerService.alertNewOrder();
        return ResponseEntity.ok(new Message("Pedido feito!", null));
    }

    @GetMapping("/order-manager")
    public ResponseEntity<?> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
