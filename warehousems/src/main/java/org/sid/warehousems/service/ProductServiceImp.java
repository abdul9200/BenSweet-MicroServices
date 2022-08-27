package org.sid.warehousems.service;

import org.sid.warehousems.entities.Product;
import org.sid.warehousems.repositories.CategoryRepository;
import org.sid.warehousems.repositories.PatisserieRepository;
import org.sid.warehousems.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;

@Service
@Transactional

public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PatisserieRepository patisserieRepository;
    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public Product addProduct(Product product, Long idPatisserie, Long idCategory) {
        product.setCategory(categoryRepository.findById(idCategory).get());
        product.setPatisserie(patisserieRepository.findById(idPatisserie).get());
        if(product.getQuantitySold()<= product.getQuantityStock())
            return productRepository.save(product);
        else
            return null;
    }
    public Product updateProduct(Product product,Long idProduct,Long idPatisserie,Long idCategory){
        product.setId(idProduct);
        product.setPatisserie(patisserieRepository.findById(idPatisserie).get());
        product.setCategory(categoryRepository.findById(idCategory).get());
        if(product.getQuantitySold()<= product.getQuantityStock())
            return productRepository.save(product);
        else
            return null;
    }

    @Override
    public Product demandeProduct(Long idproduct, int quantitydemanded) {
        Product product=productRepository.findById(idproduct).get();
        if (quantitydemanded+ product.getQuantitySold()<=product.getQuantityStock()){
            product.setQuantitySold(quantitydemanded+ product.getQuantitySold());
            product.setSelected(true);
            if(quantitydemanded+ product.getQuantitySold()==product.getQuantityStock())
                product.setAvailable(false);
            return productRepository.save(product);
        }
        else
        return null;
    }
    public Product annulerDemandeProduct(Long idproduct, int quantitydemanded) {
        Product product=productRepository.findById(idproduct).get();
        if (product.getQuantitySold()-quantitydemanded>=0){
            product.setQuantitySold(product.getQuantitySold()-quantitydemanded);
            product.setAvailable(true);
            if(product.getQuantitySold()-quantitydemanded==0)
                product.setSelected(false);
            return productRepository.save(product);
        }
        else
            return null;
    }

}
