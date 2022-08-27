package org.sid.warehousems.service;

import org.sid.warehousems.entities.Patisserie;
import org.springframework.web.bind.annotation.PathVariable;

public interface PatisserieService {
    Patisserie addPatisserie(Patisserie patisserie);
    Patisserie updatePatisserie(Patisserie patisserie,Long idPatisserie);
    Patisserie getPatisserie(Long id);


}
