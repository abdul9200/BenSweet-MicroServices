package org.sid.orderms.services;

import org.sid.orderms.entities.Order;
import org.sid.orderms.entities.Product;
import org.sid.orderms.repositories.AppUserServiceClient;
import org.sid.orderms.repositories.OrderItemRepository;
import org.sid.orderms.repositories.OrderRepository;
import org.sid.orderms.repositories.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service

public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductServiceClient productServiceClient;
    @Autowired
    private AppUserServiceClient appUserServiceClient;


    @Override
    public Order getOrder(Long id) {
        Order order=orderRepository.findById(id).get();
        order.setClient(appUserServiceClient.findAppUserById((long) order.getClientID()));
        order.setOrderItems(orderItemRepository.findByOrderId(id));
        order.getOrderItems().forEach(pi->{
            pi.setProduct(productServiceClient.findProductById(pi.getProductID()));
        });
        return order;
    }

    @Override
    public List<Order> listOrders() {
        List<Order> orders=orderRepository.findAll();
        orders.forEach(order -> {
        order.setClient(appUserServiceClient.findAppUserById((long) order.getClientID()));
        order.setOrderItems(orderItemRepository.findByOrderId(order.getId()));
        order.getOrderItems().forEach(pi->{
            pi.setProduct(productServiceClient.findProductById(pi.getProductID()));
        });
        });
        return orders;
    }

    @Override
    public Order addOrder(Order order) {
        order.setDate(new Date());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order=orderRepository.findById(id).get();
        if(order.getOrderItems()!=null) {
            order.getOrderItems().forEach(orderItem -> {
                Product product =productServiceClient.annulerDemandeProduct(orderItem.getProductID(),orderItem.getQuantity());

            });
        }
        orderRepository.deleteById(id);

    }
}
