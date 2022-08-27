package org.sid.livraisonms.Repository;

import org.sid.livraisonms.Entity.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LivraisonRepository extends JpaRepository<Livraison,Long> {

}
