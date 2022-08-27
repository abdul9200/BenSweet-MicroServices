package org.sid.warehousems.controllers;

import lombok.AllArgsConstructor;
import org.sid.warehousems.entities.Patisserie;
import org.sid.warehousems.repositories.AppUserServiceClient;
import org.sid.warehousems.repositories.PatisserieRepository;
import org.sid.warehousems.repositories.ProductRepository;
import org.sid.warehousems.service.PatisserieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class PatisserieRestController {
    @Autowired
    private PatisserieService patisserieService;
    @GetMapping("/Patisserie/full/{id}")
    Patisserie getPatisserie(@PathVariable(name="id") Long id){
        return patisserieService.getPatisserie(id);

    }
    @PostMapping("/Patisseries")
    Patisserie addPatisserie(@RequestBody Patisserie patisserie){
        return patisserieService.addPatisserie(patisserie);
    }
    @PutMapping("/Patisserie/{id}")
    Patisserie updatePatisserie(@PathVariable(name="id") Long id,@RequestBody Patisserie patisserie){
        return patisserieService.updatePatisserie(patisserie,id);
    }
}
