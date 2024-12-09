package com.example.circularinventory.repository;

import com.example.circularinventory.model.Materials;
import com.example.circularinventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}