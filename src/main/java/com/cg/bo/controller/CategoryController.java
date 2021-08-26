package com.cg.bo.controller;


import com.cg.bo.model.bussiness.Category;
import com.cg.bo.model.bussiness.Product;
import com.cg.bo.service.CategoryService;
import com.cg.bo.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView homePage() {
        return new ModelAndView("/dashboard/category/list");
    }

    @GetMapping("/allCategory")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Iterable<Category>> allCategories(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> editCategory(@RequestBody Category category,@PathVariable Long id){
        category.setCategory_id(id); ;
        return new ResponseEntity<>(categoryService.save(category),HttpStatus.OK);
    }

    @GetMapping("/edit-category/{id}")
    public ResponseEntity<Category> categoryResponseEntity(@PathVariable Long id){
        Category categoryOptional = categoryService.findById(id).get();
        return new ResponseEntity<>(categoryOptional,HttpStatus.OK);
    }
}
