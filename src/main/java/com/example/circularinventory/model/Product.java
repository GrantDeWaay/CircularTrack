package com.example.circularinventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "products", schema = "circular_inventory", indexes = {
        @Index(name = "product_type_id", columnList = "product_type_id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Column(name = "pickup_timestamp")
    private Instant pickupTimestamp;

    @Column(name = "return_timestamp")
    private Instant returnTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    @JsonIgnore
    private ProductType productType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getPickupTimestamp() {
        return pickupTimestamp;
    }

    public void setPickupTimestamp(Instant pickupTimestamp) {
        this.pickupTimestamp = pickupTimestamp;
    }

    public Instant getReturnTimestamp() {
        return returnTimestamp;
    }

    public void setReturnTimestamp(Instant returnTimestamp) {
        this.returnTimestamp = returnTimestamp;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

}