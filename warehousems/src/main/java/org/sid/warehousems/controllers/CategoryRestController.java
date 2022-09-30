package org.sid.warehousems.controllers;

import org.sid.warehousems.entities.Category;
import org.sid.warehousems.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")


public class CategoryRestController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable("id") Long id){
        return categoryService.getCategory(id);
    }

    @PostMapping("/categories")
    Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PutMapping("/categories/{id}")
    Category updateCategory(@RequestBody Category category,@PathVariable("id") Long idCategory){
        return categoryService.updateCategory(category,idCategory);
    }
    
    @DeleteMapping("/categories/{id}")
    void deleteCategory(@PathVariable("id") Long idCategory){

        categoryService.deleteCategory(idCategory);

    }

}
