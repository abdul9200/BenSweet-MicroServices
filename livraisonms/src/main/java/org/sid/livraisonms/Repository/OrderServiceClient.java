package org.sid.livraisonms.Repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="order-service")
public interface OrderServiceClient {
    @GetMapping("/orders/full/{id}")
    Order find
}
