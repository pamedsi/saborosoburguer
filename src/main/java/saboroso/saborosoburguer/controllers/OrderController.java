package saboroso.saborosoburguer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTOs.order.ClientOrderDTO;
import saboroso.saborosoburguer.model.BaseController;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.OrderService;

@RestController
public class OrderController extends BaseController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping(value = "/request-order/{client_id}")
    public ResponseEntity<?> requestOrder(@RequestBody ClientOrderDTO orderDTO, @PathVariable("client_id") String clientIdentifier) {
        orderService.makeOrder(clientIdentifier, orderDTO);
        return ResponseEntity.ok(new Message("Pedido feito!"));
    }
    @GetMapping(value = "/see-orders")
    public ResponseEntity<?> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
