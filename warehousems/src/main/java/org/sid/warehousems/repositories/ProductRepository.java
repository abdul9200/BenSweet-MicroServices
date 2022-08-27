package org.sid.warehousems.repositories;

import org.sid.warehousems.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,Long> {
    Collection<Product>findByPatisserieId(Long PatisserieID);
    Collection<Product>findByPromotionTrue();
    Collection<Product>findByAvailableTrue();
    Collection<Product>findBySelectedTrue();


}
