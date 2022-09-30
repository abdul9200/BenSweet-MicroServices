package org.sid.warehousems.service;

import org.sid.warehousems.entities.Category;
import org.sid.warehousems.repositories.CategoryRepository;
import org.sid.warehousems.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long idCategory) {
        return categoryRepository.findById(idCategory).get();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long idCategory) {
        category.setId(idCategory);
        category.setProducts(categoryRepository.findById(idCategory).get().getProducts());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        Category category=categoryRepository.findById(idCategory).get();
        if(category.getProducts()!=null)
        {
            category.getProducts().forEach(product -> {
                product.setCategory(null);
                productRepository.save(product);
            });

        }
        categoryRepository.deleteById(idCategory);


    }
}
