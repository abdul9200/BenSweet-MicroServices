package org.sid.warehousems.service;

import org.sid.warehousems.entities.Patisserie;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PatisserieService {
    Patisserie addPatisserie(Patisserie patisserie);
    Patisserie updatePatisserie(Patisserie patisserie,Long idPatisserie);
    Patisserie getPatisserie(Long id);
    List<Patisserie> getPatisseries();
    void deletePatisserie(Long id);
    Patisserie addSold(Long idPatisserie,double solde);
    Patisserie removeSold(Long idPatisserie,double solde);


}
