package com.example.circularinventory.service;

import com.example.circularinventory.model.Material;
import com.example.circularinventory.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialsService {

    private final MaterialRepository materialRepository;


    @Autowired
    public MaterialsService( MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public void addBalanceToMaterial(Material material, int addAmount) {
        if(material.getAmount() == null){
            material.setAmount(addAmount);
        }
        else {
            material.setAmount(material.getAmount() + addAmount);
        }
    }

    public Material createNewMaterial(String name, @Nullable Integer addAmount) {
        if(materialExists(name)){
            return null;
        }
        Material material = new Material();
        material.setName(name);
        if(addAmount != null){
            addBalanceToMaterial(material, addAmount);
        }
        materialRepository.save(material);
        return material;
    }

    public boolean materialExists(String name) {
        return materialRepository.existsByName(name);
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
}
