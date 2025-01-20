package com.example.circularinventory.repository;

import com.example.circularinventory.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    boolean existsByName(String name);
}