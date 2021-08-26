package com.cg.bo.service;

import com.cg.bo.model.bussiness.Product;

public interface ProductService extends GeneralService<Product> {
    Iterable<Product> findAllByOrderByProduct_idDesc();

    Iterable<Product> findAllProduct_idDesc();

    void restoreProductById(Long id);

    Iterable<Product> findAllByCategoryCategory_id(Long id);
}
