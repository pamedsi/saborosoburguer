package saboroso.saborosoburguer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTOs.order.ClientOrderDTO;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.OrderService;

@RestController
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    @RequestMapping(value = "/request-order/{client_id}")
    public ResponseEntity<?> requestOrder(@RequestBody ClientOrderDTO orderDTO, @PathVariable("client_id") String clientIdentifier) {
        orderService.makeOrder(clientIdentifier, orderDTO);
        return ResponseEntity.ok(new Message("Pedido feito!"));
    }
    @GetMapping
    @RequestMapping (value = "/see-orders")
    public ResponseEntity<?> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
