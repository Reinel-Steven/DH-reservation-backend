package com.reist.reservation.services;

import com.reist.reservation.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save (Product product);
    void delete(Long id);

}
