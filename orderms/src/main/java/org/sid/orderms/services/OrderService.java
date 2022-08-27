package org.sid.orderms.services;

import org.sid.orderms.entities.Order;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderService {
    public Order getOrder(Long id);
    public List<Order> listOrders();
    public Order addOrder(Order order);
    public void deleteOrder(Long id);
}
