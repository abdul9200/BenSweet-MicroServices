package org.sid.userms.repositories;

import org.sid.userms.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface RoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
