package com.example.circularinventory.repository;

import com.example.circularinventory.model.Materials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialsRepository extends JpaRepository<Materials, Integer> {

    boolean existsByName(String name);
}