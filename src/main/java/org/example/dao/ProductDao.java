package org.example.dao;

import org.example.entity.Product;
import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findById(int id);
    List<Product> findByNameContaining(String text);
    void addProduct(String name, String category, double price);
    void updateProduct(int id, String name, String category, double price);
    void deleteById(int id);
    List<Product> findAllOrderByPrice();
    List<Product> findAllOrderByPriceDesc();
    List<Product> findAllOrderByName();
    List<Product> findAllOrderByNameDesc();
    List<Product> findAllOrderByCategory();
    List<Product> findAllOrderByCategoryDesc();
    List<Product> findByName(String name);
    List<Product> findByCategory(String category);
}

