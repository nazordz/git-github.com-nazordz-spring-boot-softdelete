package com.softdelete.softdeletedemo.service;

import javax.persistence.EntityManager;

import com.softdelete.softdeletedemo.entity.Product;
import com.softdelete.softdeletedemo.repository.ProductRepository;

import org.hibernate.Session;
import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;
    
    public Product create(Product product) {
        return productRepository.save(product);
    }

    public void remove(String id) {
        productRepository.deleteById(id);
    }

    public Iterable<Product> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("softDeleteFilter");
        filter.setParameter("isDeleted", false);
        Iterable<Product> products = productRepository.findAll();
        session.disableFilter("softDeleteFilter");
        return products;
    }
    
}
