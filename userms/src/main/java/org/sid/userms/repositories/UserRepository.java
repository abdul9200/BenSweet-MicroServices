package org.sid.userms.repositories;

import org.sid.userms.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface UserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);

}
