package org.sid.orderms.repositories;

import org.sid.orderms.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="warehouse-service")
public interface ProductServiceClient {
    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable("id") Long id);

    @GetMapping("/products")
    Product findAll();

    @PutMapping(path = "/products/{idProd}/demand/{idDemand}")
    Product demandeProduct(@PathVariable(name = "idProd") Long idproduct, @PathVariable(name = "idDemand") int quantitydemanded);

    @PutMapping(path = "/products/{idProd}/annulerdemanded/{idDemand}")
    Product annulerDemandeProduct(@PathVariable(name = "idProd") Long idproduct, @PathVariable(name = "idDemand") int quantitydemanded);
}