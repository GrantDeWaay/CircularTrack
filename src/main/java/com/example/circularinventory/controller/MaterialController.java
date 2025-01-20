package com.example.circularinventory.controller;


import com.example.circularinventory.dto.AddMaterialDto;
import com.example.circularinventory.model.Material;
import com.example.circularinventory.service.MaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialsService materialService;

    @Autowired
    public MaterialController(MaterialsService materialsService) {
        this.materialService = materialsService;
    }

    @PostMapping("/add-material")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Material> addMaterial(@RequestBody AddMaterialDto addMaterialDto) {
        Material material = materialService.createNewMaterial(addMaterialDto.name, addMaterialDto.quantity);
        return ResponseEntity.ok(material);
    }

    @GetMapping("/get-materials")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Material>> getMaterials() {
        List<Material> productTypeMaterialList = materialService.getAllMaterials();
        return ResponseEntity.ok(productTypeMaterialList);
    }
}
