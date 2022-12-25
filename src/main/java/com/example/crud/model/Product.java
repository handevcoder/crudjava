package com.example.crud.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product_name;

    private int jumlah;

    private Product() {}

    public Product(Long id, String product_name, int jumlah) {
        this.id = id;
        this.product_name = product_name;
        this.jumlah = jumlah;
    }

}