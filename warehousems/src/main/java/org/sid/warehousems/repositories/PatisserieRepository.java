package org.sid.warehousems.repositories;

import org.sid.warehousems.entities.Patisserie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RepositoryRestResource
public interface PatisserieRepository extends JpaRepository<Patisserie,Long> {
    long countByModerateurID(double id);
}
