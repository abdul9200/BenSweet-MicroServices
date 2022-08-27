package org.sid.warehousems.service;

import org.sid.warehousems.entities.Product;

public interface ProductService {

    Product addProduct(Product product,Long idPatisserie,Long idCategory);
    Product updateProduct(Product product,Long idProduct,Long idPatisserie,Long idCategory);
    Product demandeProduct(Long idproduct , int quantitydemanded);
    Product annulerDemandeProduct(Long idproduct, int quantitydemanded);

}
