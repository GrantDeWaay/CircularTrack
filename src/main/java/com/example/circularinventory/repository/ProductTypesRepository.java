package com.example.circularinventory.repository;

import com.example.circularinventory.model.Product;
import com.example.circularinventory.model.ProductTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypesRepository extends JpaRepository<ProductTypes, Integer> {

}