package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTOs.burger.InputBurgerDTO;
import saboroso.saborosoburguer.model.BurgerCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table
public class Burger{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter (AccessLevel.NONE)
    @Id
    private Long id;
    @Column
    @Setter(AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
    @Column
    private String title = null;
    @Column
    private BigDecimal price = null;
    @Column
    private Boolean inStock = true;
    @Column
    private Boolean deleted = false;
    @Column
    private BurgerCategory category;
    @Column (length = 2000)
    private String pic;
    @ManyToMany
    @JoinTable(name = "burger_ingredient",
            joinColumns = @JoinColumn(name = "burger_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;
    public Burger(InputBurgerDTO inputBurger) {
        category = BurgerCategory.valueOf(inputBurger.category().toUpperCase().replace(" ", "_"));
        setTitle(inputBurger.title());
        setPrice(inputBurger.price().setScale(2, RoundingMode.HALF_UP));
        setPic(inputBurger.pic());
        if (inputBurger.inStock() != null) setInStock(inputBurger.inStock());
        else setInStock(true);
        if (inputBurger.deleted() != null) setDeleted(inputBurger.deleted());
        else setDeleted(false);
    }
}