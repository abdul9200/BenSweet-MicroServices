package org.sid.orderms.repositories;

import org.sid.orderms.entities.Patisserie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@FeignClient(name="warehouse-service")
public interface PatisserieServiceClient {
    @PutMapping("/Patisseries/{idPatisserie}/addSolde/{solde}")
    Patisserie addSoldPatisserie(@PathVariable(name="idPatisserie")Long idPatisseirie, @PathVariable(name="solde") double solde);

    @PutMapping("/Patisseries/{idPatisserie}/removeSolde/{solde}")
    Patisserie removeSoldPatisserie(@PathVariable(name="idPatisserie")Long idPatisseirie, @PathVariable(name="solde") double solde);
}
