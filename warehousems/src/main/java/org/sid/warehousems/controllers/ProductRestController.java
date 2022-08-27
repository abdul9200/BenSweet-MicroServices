package org.sid.warehousems.controllers;

import org.sid.warehousems.entities.Product;
import org.sid.warehousems.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;
    @PostMapping(path="/products/patisserie/{idPat}/categorie/{idCat}")
    Product addProduct(@PathVariable(name="idCat") Long idCat, @PathVariable(name = "idPat") Long idPat, @RequestBody Product product){
        return productService.addProduct(product,idPat,idCat);
    }
    @PutMapping(path="/products/{idProd}/patisserie/{idPat}/categorie/{idCat}")
    Product updateProduct(@PathVariable(name="idProd") Long idProd,@PathVariable(name="idCat") Long idCat, @PathVariable(name = "idPat") Long idPat, @RequestBody Product product){
        return productService.updateProduct(product,idProd,idPat,idCat);
    }
    @PutMapping(path = "products/{idProd}/demand/{idDemand}")
    Product demandeProduct(@PathVariable(name="idProd") Long idproduct , @PathVariable(name="idDemand") int quantitydemanded){
        System.out.println("modify product");
        return productService.demandeProduct(idproduct,quantitydemanded);
    }
    @PutMapping(path = "products/{idProd}/annulerdemanded/{idDemand}")
    Product annulerDemandeProduct(@PathVariable(name="idProd") Long idproduct , @PathVariable(name="idDemand") int quantitydemanded){
        return productService.annulerDemandeProduct(idproduct,quantitydemanded);
    }
}
