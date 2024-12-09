package com.example.circularinventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "api_keys", schema = "circular_inventory")
public class ApiKey {
    @Id
    @Column(name = "api_key_id", nullable = false)
    private Integer id;

    @Column(name = "api_key_value", length = 45)
    private String apiKeyValue;

    @Column(name = "date_activated")
    private Instant dateActivated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiKeyValue() {
        return apiKeyValue;
    }

    public void setApiKeyValue(String apiKeyValue) {
        this.apiKeyValue = apiKeyValue;
    }

    public Instant getDateActivated() {
        return dateActivated;
    }

    public void setDateActivated(Instant dateActivated) {
        this.dateActivated = dateActivated;
    }

}