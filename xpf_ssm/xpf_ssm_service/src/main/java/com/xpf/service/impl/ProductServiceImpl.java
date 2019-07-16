package com.xpf.service.impl;

import com.github.pagehelper.PageHelper;
import com.xpf.domian.Product;
import com.xpf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xpf.dao.*;
import java.util.List;

@Service
@Transactional

public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public void deleteProduct(String id) throws Exception {
        productDao.deleteProduct(id);
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        productDao.updateProduct(product);
    }

    @Override
    public Product findById(String id) throws Exception {
        return productDao.findById(id);
    }

}
