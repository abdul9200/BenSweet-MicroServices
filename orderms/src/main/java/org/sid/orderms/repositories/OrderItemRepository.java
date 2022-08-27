package org.sid.orderms.repositories;

import org.sid.orderms.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    Collection<OrderItem> findByOrderId(Long orderID);
}
