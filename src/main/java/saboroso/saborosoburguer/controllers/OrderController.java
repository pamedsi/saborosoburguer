package saboroso.saborosoburguer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTO.order.UpdateOrderStatus;
import saboroso.saborosoburguer.DTO.order.postOrder.OrderForPostDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.Message;
import saboroso.saborosoburguer.services.OrderService;
import saboroso.saborosoburguer.services.WebSocketHandlerService;

import java.util.List;

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
    @GetMapping("/unfinished-orders")
    public ResponseEntity<?> getUnfinishedOrdersOrders() {
        return ResponseEntity.ok(orderService.getUnfinishedOrders());
    }

    @PutMapping("/update-order-status")
    public ResponseEntity<?> updateOrder(@RequestBody UpdateOrderStatus orderStatusUpdate) {
        orderService.changeOrderStatus(orderStatusUpdate);
        return ResponseEntity.ok(new Message("Pedido atualizado", List.of("Atual status: " + orderStatusUpdate.status())));
    }

}
