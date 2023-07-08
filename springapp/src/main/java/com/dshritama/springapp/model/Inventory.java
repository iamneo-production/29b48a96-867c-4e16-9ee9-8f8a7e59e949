package com.dshritama.springapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long quantity;
    private String category;
    private Long price;
    private String supplier;

    public Inventory() {}

    public Inventory(Long id, String name, Long quantity, String category, Long price, String supplier) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", name=" + name + ", quantity=" + quantity + ", category=" + category + ", price=" + price
                + ", supplier=" + supplier + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

}
