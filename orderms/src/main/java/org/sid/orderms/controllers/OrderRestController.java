package org.sid.orderms.controllers;

import lombok.AllArgsConstructor;
import org.sid.orderms.entities.AppUser;
import org.sid.orderms.entities.Order;
import org.sid.orderms.repositories.AppUserServiceClient;
import org.sid.orderms.repositories.OrderItemRepository;
import org.sid.orderms.repositories.OrderRepository;
import org.sid.orderms.repositories.ProductServiceClient;
import org.sid.orderms.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orderslist/{id}")
    Order getOrder(@PathVariable(name="id")Long id){
        return orderService.getOrder(id);
    }
    @GetMapping("/orderslist")
    List<Order> getOrders(){
        return orderService.listOrders();
    }
    @PostMapping("/orders")
    Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }
    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable(name = "id")Long idOrder){
        orderService.deleteOrder(idOrder);
    }

}
