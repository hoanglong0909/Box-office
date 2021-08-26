package com.cg.bo.controller;

import com.cg.bo.model.bussiness.Category;
import com.cg.bo.model.bussiness.Product;
import com.cg.bo.service.CategoryService;
import com.cg.bo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView listProduct(){
        return new ModelAndView("dashboard/product/list");
    }

    @GetMapping("/allCategory")
    public ResponseEntity<Iterable<Category>> allCategories(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/allProduct")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Iterable<Product>> allProducts(){
        return new ResponseEntity<>(productService.findAllByOrderByProduct_idDesc(), HttpStatus.OK);
    }

    @PostMapping("/createNewProduct")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product){
//        new Product().validate(product, bindingResult);
//        if (bindingResult.hasFieldErrors()){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        Category category = categoryService.findById(product.getCategory().getCategory_id()).get();
        product.setCategory(category);
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/createNewProduct")
    public ModelAndView createProductForm(){
        return new ModelAndView("dashboard/product/create");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody Product product,@PathVariable Long id){
        product.setProduct_id(id); ;
        product.getCategory().setCategory_name(categoryService.findById(product.getCategory().getCategory_id()).get().getCategory_name());
        return new ResponseEntity<>(productService.save(product),HttpStatus.OK);
    }

    @GetMapping("/edit-product/{id}")
    public ResponseEntity<Product> productResponseEntity(@PathVariable Long id){
        Product productOptional = productService.findById(id).get();
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> restoreProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.restoreProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/hiddenProduct")
    public ModelAndView getAllProductHiddenPage() {
        return new ModelAndView("dashboard/product/hiddenList");
    }
    @GetMapping("/allHiddenProduct")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Iterable<Product>> allHiddenProducts(){
        return new ResponseEntity<>(productService.findAllProduct_idDesc(), HttpStatus.OK);
    }

    @GetMapping("/findProduct/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product product = productService.findById(id).get();
        product.setAmount(1L);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
