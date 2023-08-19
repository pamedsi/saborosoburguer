package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue (strategy = GenerationType.UUID)
    private String identifier;
    @Column (nullable = false)
    private String title;
    @Column (nullable = true)
    private Integer grams;
}