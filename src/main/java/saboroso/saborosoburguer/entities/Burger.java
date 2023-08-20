package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saboroso.saborosoburguer.DTOs.burger.InputBurgerDTO;
import saboroso.saborosoburguer.model.BurgerCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Burger {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Setter(AccessLevel.NONE)
    private String identifier;
    @Column
    private BurgerCategory category;
    @Column (unique = true, nullable = false)
    private String title;
    @Column (nullable = false)
    private BigDecimal price;
    @Column (length = 2000)
    private String pic;
    @Column
    private Boolean inStock;
    @Column
    private Boolean deleted;
    @ManyToMany
    @JoinTable(name = "burger_ingredient",
            joinColumns = @JoinColumn(name = "burger_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;
    public Burger(InputBurgerDTO inputBurger) {
        identifier = UUID.randomUUID().toString();
        category = BurgerCategory.valueOf(inputBurger.category().toUpperCase().replace(" ", "_"));
        title = inputBurger.title();
        price = inputBurger.price().setScale(2, RoundingMode.HALF_UP);
        pic = inputBurger.pic();
        if (inputBurger.inStock() != null) inStock = inputBurger.inStock();
        else inStock = true;
        if (inputBurger.deleted() != null) inStock = inputBurger.inStock();
        else deleted = false;
    }
}