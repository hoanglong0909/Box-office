package com.cg.bo.service.impl;

import com.cg.bo.model.bussiness.Product;
import com.cg.bo.repository.ProductRepository;
import com.cg.bo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAllByOrderByProduct_idDesc() {
        return productRepository.findAllByOrderByProduct_idDesc();
    }

    @Override
    public Iterable<Product> findAllProduct_idDesc() {
        return productRepository.findAllProduct_idDesc();
    }

    @Override
    public void restoreProductById(Long id) {
        productRepository.restoreProductById(id);
    }



    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public Iterable<Product> findAllByCategoryCategory_id(Long id){
        return productRepository.findAllByCategoryCategory_id(id);
    }
}
