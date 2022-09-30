package org.sid.orderms.services;

import org.sid.orderms.entities.Order;
import org.sid.orderms.entities.OrderItem;
import org.sid.orderms.entities.Patisserie;
import org.sid.orderms.entities.Product;
import org.sid.orderms.repositories.OrderItemRepository;
import org.sid.orderms.repositories.OrderRepository;
import org.sid.orderms.repositories.PatisserieServiceClient;
import org.sid.orderms.repositories.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class OrderItemServiceImp implements OrderItemService{
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ProductServiceClient productServiceClient;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PatisserieServiceClient patisserieServiceClient;
    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {

        Product product =productServiceClient.demandeProduct(orderItem.getProductID(),orderItem.getQuantity());
        if (product==null){
            return null;
        }

        orderItem.setPrice(productServiceClient.findProductById(orderItem.getProductID()).getCurrentPrice()* orderItem.getQuantity());
        Order order=orderItem.getOrder();
        if (order!=null)
            order.setTotalAmount(order.getTotalAmount()+orderItem.getPrice());
        Patisserie patisserie=productServiceClient.getPatisserie(product.getId());
        patisserieServiceClient.addSoldPatisserie(patisserie.getId(),patisserie.getSolde()+orderItem.getPrice());
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem addOrderItem(OrderItem orderItem, Long idOrder) {
        Product product =productServiceClient.demandeProduct(orderItem.getProductID(),orderItem.getQuantity());
        if (product==null){
            return null;
        }

        orderItem.setPrice(productServiceClient.findProductById(orderItem.getProductID()).getCurrentPrice()* orderItem.getQuantity());
        Order order= orderRepository.findById(idOrder).get();
        order.setTotalAmount(order.getTotalAmount()+orderItem.getPrice());
        orderItem.setOrder(order);
        Patisserie patisserie=productServiceClient.getPatisserie(product.getId());
        patisserieServiceClient.addSoldPatisserie(patisserie.getId(),patisserie.getSolde()+orderItem.getPrice());
        return orderItemRepository.save(orderItem);

    }


    @Override
    public void deleteOrderItem(Long idOrderItem) {
       OrderItem orderItem=orderItemRepository.findById(idOrderItem).get();
       Product product =productServiceClient.annulerDemandeProduct(orderItem.getProductID(),orderItem.getQuantity());
        if (product!=null){
            Order order =orderItem.getOrder();
            if(order!=null)
                order.setTotalAmount(order.getTotalAmount()-orderItem.getPrice());
            Patisserie patisserie=productServiceClient.getPatisserie(product.getId());
            patisserieServiceClient.removeSoldPatisserie(patisserie.getId(),patisserie.getSolde()+orderItem.getPrice());
            orderItemRepository.delete(orderItem);
        }



    }

    @Override
    public List<OrderItem> listOrderItem() {
        List<OrderItem> orderItems=orderItemRepository.findAll();
        orderItems.forEach(pi->{
            pi.setProduct(productServiceClient.findProductById(pi.getProductID()));
        });
        return orderItems;
    }

    @Override
    public OrderItem getOrderItem(Long idOrderItem) {

        OrderItem orderItem= orderItemRepository.findById(idOrderItem).get();
        orderItem.setProduct(productServiceClient.findProductById(orderItem.getProductID()));
        return orderItem;
    }

    @Override
    public OrderItem addOrderItemToOrder(Long idOrder, OrderItem orderItem) {
        orderItem.setOrder(orderRepository.findById(idOrder).get());
        Product product=productServiceClient.demandeProduct(orderItem.getProductID(),orderItem.getQuantity());
        orderItem.setPrice(product.getCurrentPrice()*orderItem.getQuantity());
        orderItem.getOrder().setTotalAmount(orderItem.getOrder().getTotalAmount()+orderItem.getPrice());
        Patisserie patisserie=productServiceClient.getPatisserie(product.getId());
        patisserieServiceClient.addSoldPatisserie(patisserie.getId(),patisserie.getSolde()+orderItem.getPrice());
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem addOrderItemToOrder(Long idOrder, Long idOrderItem) {
        OrderItem orderItem=orderItemRepository.findById(idOrderItem).get();
        orderItem.setOrder(orderRepository.findById(idOrder).get());
        orderItem.getOrder().setTotalAmount(orderItem.getOrder().getTotalAmount()+orderItem.getPrice());
        Patisserie patisserie=productServiceClient.getPatisserie(orderItem.getProduct().getId());
        patisserieServiceClient.addSoldPatisserie(patisserie.getId(),patisserie.getSolde()+orderItem.getPrice());

        return orderItemRepository.save(orderItem);
    }
}
