package org.sid.orderms.controllers;

import org.sid.orderms.entities.OrderItem;
import org.sid.orderms.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class OrderItemRestController {
    @Autowired
    private OrderItemService orderItemService;
    @GetMapping(path = "/orderItems")
    public List<OrderItem> getOrderItems(){
        return orderItemService.listOrderItem();
    }
    @GetMapping(path = "/orderItems/{id}")
    public OrderItem getOrderItemsById(@PathVariable(name = "id")Long id){
        return orderItemService.getOrderItem(id);
    }
    @PostMapping(path = "/orderItems")
    public OrderItem insertOrderItem(@RequestBody OrderItem orderItem){
        return orderItemService.addOrderItem(orderItem);
    }
    @PostMapping(path = "/orderItems/{idOrder}")
    public OrderItem insertOrderItem(@RequestBody OrderItem orderItem,@PathVariable(name="idOrder")Long idOrder){
        return orderItemService.addOrderItem(orderItem, idOrder);
    }
    @PutMapping(path="/orderItems/{idOrderItem}/order/{idOrder}")
    public OrderItem addOrderItemToOrder(@PathVariable(name="idOrderItem")Long idOrderItem,@PathVariable("idOrder")Long idOrder){
        return orderItemService.addOrderItemToOrder(  idOrder,idOrderItem);

    }
    @PutMapping(path="/orderItems/order/{idOrder}")
    public OrderItem addOrderItemToOrder(@RequestBody OrderItem orderItem,@PathVariable("idOrder")Long idOrder){
        return orderItemService.addOrderItemToOrder(idOrder,orderItem);
    }

    @DeleteMapping(path="/orderItems/{id}")
    public void deleteOrderItem(@PathVariable(name="id")Long id){
         orderItemService.deleteOrderItem(id);
    }

}
