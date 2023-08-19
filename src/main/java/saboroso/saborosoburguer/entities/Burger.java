package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import saboroso.saborosoburguer.model.BurgerCategory;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Burger {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue (strategy = GenerationType.UUID)
    private String identifier;
    @Column
    private BurgerCategory category;
    @Column (unique = true)
    private String title;
    @Column
    private BigDecimal price;
    @Column
    private Boolean inStock;
    @ManyToMany
    @JoinTable(name = "burger_ingredient",
            joinColumns = @JoinColumn(name = "burger_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;
}