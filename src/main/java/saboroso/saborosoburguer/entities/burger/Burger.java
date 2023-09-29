package saboroso.saborosoburguer.entities.burger;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTO.burger.InputBurgerDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@Table
public class Burger{
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Setter (AccessLevel.NONE)
    @Getter (AccessLevel.NONE)
    private Long id;
    @Column
    @Setter(AccessLevel.NONE)
    private String identifier;
    @Column (unique = true)
    private String title;
    @Column
    private BigDecimal price;
    @Column
    private Boolean inStock;
    @Column
    private Boolean deleted;
    @ManyToOne
    @JoinTable (
            name = "category_of_burger",
            joinColumns = @JoinColumn(name = "burger_id"),
            inverseJoinColumns = @JoinColumn(name = "burger_category_id")
    )
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
        setInStock(inputBurger.inStock());
        createdAt = LocalDateTime.now();
        deleted = false;
    }

    public void addIngredient(Ingredient ingredient){
        if (this.ingredients == null) this.ingredients = new ArrayList<>();
        this.ingredients.add(ingredient);
    }
    public void removeIngredient(Ingredient ingredient) {
        if (this.ingredients != null) this.ingredients.remove(ingredient);
    }
}