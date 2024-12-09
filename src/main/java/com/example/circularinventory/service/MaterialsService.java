package com.example.circularinventory.service;

import com.example.circularinventory.model.Materials;
import com.example.circularinventory.model.Product;
import com.example.circularinventory.repository.MaterialsRepository;
import com.example.circularinventory.repository.ProductRepository;
import com.example.circularinventory.repository.ProductTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialsService {

    private final MaterialsRepository materialsRepository;


    @Autowired
    public MaterialsService( MaterialsRepository materialsRepository) {
        this.materialsRepository = materialsRepository;
    }

    public void addBalanceToMaterial(Materials material, int addAmount) {
        if(material.getAmount() == null){
            material.setAmount(addAmount);
        }
        else {
            material.setAmount(material.getAmount() + addAmount);
        }
    }

    public Materials createNewMaterial(String name, @Nullable Integer addAmount) {
        if(materialExists(name)){
            return null;
        }
        Materials material = new Materials();
        material.setName(name);
        if(addAmount != null){
            addBalanceToMaterial(material, addAmount);
        }
        materialsRepository.save(material);
        return material;
    }

    public boolean materialExists(String name) {
        return materialsRepository.existsByName(name);
    }

    public List<Materials> getAllMaterials() {
        return materialsRepository.findAll();
    }
}
