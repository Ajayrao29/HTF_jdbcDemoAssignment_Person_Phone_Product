package org.example.dao;

import org.example.entity.Phone;
import java.util.List;

public interface PhoneDao {
    List<Phone> findAll();
    Phone findById(int id);
    List<Phone> findByBrandContaining(String text);
    void addPhone(String brand, String model, double price, String releaseDate);
    void updatePhone(int id, String brand, String model, double price);
    void deleteById(int id);
    List<Phone> findAllOrderByPrice();
    List<Phone> findAllOrderByPriceDesc();
    List<Phone> findAllOrderByBrand();
    List<Phone> findAllOrderByBrandDesc();
    List<Phone> findAllOrderByModel();
    List<Phone> findAllOrderByModelDesc();
    List<Phone> findAllOrderByReleaseDate();
    List<Phone> findByBrand(String brand);
    List<Phone> findByModel(String model);
}

