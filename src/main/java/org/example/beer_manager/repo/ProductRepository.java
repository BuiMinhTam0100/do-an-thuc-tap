package org.example.beer_manager.repo;

import org.example.beer_manager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select pr from Product pr left join fetch pr.category")
    List<Product> findAllProduct();
}