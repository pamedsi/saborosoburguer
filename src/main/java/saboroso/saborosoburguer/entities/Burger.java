package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTOs.burger.InputBurgerDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@Table
public class Burger{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    @Id
    private Long id;
    @Column
    @Setter(AccessLevel.NONE)
    private String identifier;
    @Column
    private String title;
    @Column
    private BigDecimal price;
    @Column
    private Boolean inStock;
    @Column
    private Boolean deleted;
    @ManyToOne
    private BurgerCategory burgerCategory;
    @Column (length = 2000)
    private String pic;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime lastEditedIn;
    @ManyToMany
    @JoinTable(name = "burger_ingredient",
            joinColumns = @JoinColumn(name = "burger_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;
    public Burger(InputBurgerDTO inputBurger) {
        identifier = UUID.randomUUID().toString();
        setTitle(inputBurger.title());
        setPrice(inputBurger.price().setScale(2, RoundingMode.HALF_UP));
        setPic(inputBurger.pic());
        if (inputBurger.inStock() != null) setInStock(inputBurger.inStock());
        else setInStock(true);
        if (inputBurger.deleted() != null) setDeleted(inputBurger.deleted());
        else setDeleted(false);
        createdAt = LocalDateTime.now();
    }
}