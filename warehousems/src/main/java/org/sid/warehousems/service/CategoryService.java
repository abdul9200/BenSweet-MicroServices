package org.sid.warehousems.service;

import org.sid.warehousems.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategory(Long idCategory);
    Category addCategory(Category category);
    Category updateCategory(Category category,Long idCategory);
    void deleteCategory(Long idCategory);

}
