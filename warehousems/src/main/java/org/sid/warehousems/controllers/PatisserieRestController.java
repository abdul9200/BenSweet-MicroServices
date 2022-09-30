package org.sid.warehousems.controllers;

import lombok.AllArgsConstructor;
import org.sid.warehousems.entities.Patisserie;
import org.sid.warehousems.repositories.AppUserServiceClient;
import org.sid.warehousems.repositories.PatisserieRepository;
import org.sid.warehousems.repositories.ProductRepository;
import org.sid.warehousems.service.PatisserieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PatisserieRestController {
    @Autowired
    private PatisserieService patisserieService;
    @GetMapping("/patisseries")
    List<Patisserie>getPatisseries(){
        return  patisserieService.getPatisseries();
    }
    @GetMapping("/patisseries/{id}")
    Patisserie getPatisserie(@PathVariable(name="id") Long id){
        return patisserieService.getPatisserie(id);

    }
    @PostMapping("/Patisseries")
    Patisserie addPatisserie(@RequestBody Patisserie patisserie){
        return patisserieService.addPatisserie(patisserie);
    }
    @PutMapping("/Patisseries/{id}")
    Patisserie updatePatisserie(@PathVariable(name="id") Long id,@RequestBody Patisserie patisserie){
        return patisserieService.updatePatisserie(patisserie,id);
    }
    @DeleteMapping("Patisseries/{id}")
    void deletePatisserie(@PathVariable(name="id")Long id){
        patisserieService.deletePatisserie(id);
    }
    @PutMapping("/Patisseries/{idPatisserie}/addSolde/{solde}")
    Patisserie addSoldPatisserie(@PathVariable(name="idPatisserie")Long idPatisseirie,@PathVariable(name="solde") double solde){
        return patisserieService.addSold(idPatisseirie,solde);
    }
    @PutMapping("/Patisseries/{idPatisserie}/removeSolde/{solde}")
    Patisserie removeSoldPatisserie(@PathVariable(name="idPatisserie")Long idPatisseirie,@PathVariable(name="solde") double solde){
        return patisserieService.removeSold(idPatisseirie,solde);
    }
}
