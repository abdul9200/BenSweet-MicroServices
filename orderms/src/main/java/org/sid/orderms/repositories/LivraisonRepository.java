package org.sid.orderms.repositories;

import org.sid.orderms.entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LivraisonRepository extends JpaRepository<Livraison,Long> {
}
