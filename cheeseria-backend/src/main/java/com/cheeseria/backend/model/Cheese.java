package com.cheeseria.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="cheeses")

// Boilerplate Functions Handled by lombox
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Cheese {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double pricePerKilo;
    private String colour;
    private String imageURL;
}
