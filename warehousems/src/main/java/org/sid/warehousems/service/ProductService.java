package org.sid.warehousems.service;

import org.sid.warehousems.entities.Patisserie;
import org.sid.warehousems.entities.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product,Long idPatisserie,Long idCategory);
    Product updateProduct(Product product,Long idProduct,Long idPatisserie,Long idCategory);
    Product demandeProduct(Long idproduct , int quantitydemanded);
    Product annulerDemandeProduct(Long idproduct, int quantitydemanded);
    Product getProduct(Long idProduct);
    List<Product> getProducts();
    List<Product> getProductsByPatisserie(Long id);
    List<Product> getProductsByCategory(Long id);
    void deleteProduct(Long id);
    Patisserie getPatisserie(Long idProduct);

}
