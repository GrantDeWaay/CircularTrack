package com.example.circularinventory.dto;

import java.util.List;

public class ProductTypeResponseDto {
    private Integer id;
    private String name;
    private List<MaterialDto> materials;

    public static class MaterialDto {
        private Integer materialId;
        private String materialName;
        private Integer amount;
        private Integer retrievablePercent;

        // Getters and setters
        public Integer getMaterialId() {
            return materialId;
        }

        public void setMaterialId(Integer materialId) {
            this.materialId = materialId;
        }

        public String getMaterialName() {
            return materialName;
        }

        public void setMaterialName(String materialName) {
            this.materialName = materialName;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public Integer getRetrievablePercent() {
            return retrievablePercent;
        }

        public void setRetrievablePercent(Integer retrievablePercent) {
            this.retrievablePercent = retrievablePercent;
        }
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MaterialDto> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialDto> materials) {
        this.materials = materials;
    }
}