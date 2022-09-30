package org.sid.orderms.repositories;

import org.sid.orderms.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<List<Order>> findOrderByClientID(long id);
}
