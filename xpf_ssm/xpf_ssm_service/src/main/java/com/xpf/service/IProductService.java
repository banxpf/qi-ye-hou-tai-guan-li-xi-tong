package com.xpf.service;

import com.xpf.dao.IProductDao;
import com.xpf.domian.Product;

import java.util.List;


public interface IProductService {

    public List<Product> findAll(int page,int size) throws  Exception;

   public void save(Product product) throws Exception;

    void deleteProduct(String id) throws Exception;

    void updateProduct(Product product) throws Exception;

    Product findById(String id) throws Exception;
}
