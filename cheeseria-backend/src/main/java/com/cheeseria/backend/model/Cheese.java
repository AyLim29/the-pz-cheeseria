package com.cheeseria.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@ToString
public class Cheese {
    @Id
    private Long id;
    private String name;
    private double pricePerKilo;
    private String colour;
    private String imageURL;
}
