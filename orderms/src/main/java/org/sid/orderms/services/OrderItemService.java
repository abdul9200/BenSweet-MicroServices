package org.sid.orderms.services;

import org.sid.orderms.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    public OrderItem addOrderItem(OrderItem orderItem);
    public OrderItem addOrderItem(OrderItem orderItem,Long idOrder);
    public void deleteOrderItem(Long idOrderItem);
    public List<OrderItem> listOrderItem();
    public OrderItem getOrderItem(Long idOrderItem);
    public OrderItem addOrderItemToOrder(Long idOrder,OrderItem orderItem);
    public OrderItem addOrderItemToOrder(Long idOrder,Long idOrderItem);
}
