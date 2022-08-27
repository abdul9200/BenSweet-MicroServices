package org.sid.orderms.controllers;

import org.sid.orderms.entities.Livraison;
import org.sid.orderms.services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LivraisonRestController {
    @Autowired
    private LivraisonService livraisonService;
    @PostMapping("/livraisons/{idOrder}")
    public Livraison addLivraison(@RequestBody Livraison livraison, @PathVariable("idOrder")Long idOrder){
        return livraisonService.addLivraison(livraison,idOrder);
    }
    @PutMapping("/livraisons/{idLivraison}")
    public  Livraison updateLivraison(@RequestBody Livraison livraison,@PathVariable("idLivraison") Long idLivraison){
        return  livraisonService.updateLivraison(idLivraison,livraison);
    }
    @PutMapping("/livraisons/{idLivraison}/order/{idOrder}")
    public  Livraison updateLivraison(@RequestBody Livraison livraison,@PathVariable("idLivraison") Long idLivraison,@PathVariable("idOrder") Long idOrder){
        return  livraisonService.updateLivraison(idLivraison,livraison,idOrder);
    }
    @PutMapping("/livraison/{idLivraison}/orders/{idOrder}")
    public  Livraison addLivraisonToOrder(@PathVariable("idLivraison") Long idLivraison,@PathVariable("idOrder") Long idOrder){
        return  livraisonService.addLivraisonToOrder(idLivraison,idOrder);
    }
    @DeleteMapping("/livraisons/{id}")
    public void deleteLivraison(@PathVariable("id")Long id){
        livraisonService.removeLivraison(id);

    }
}
