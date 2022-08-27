package org.sid.warehousems.repositories;

import org.sid.warehousems.entities.Category;
import org.sid.warehousems.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
