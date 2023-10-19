package saboroso.saborosoburguer.entities.menuItems.burger;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTO.burger.InputBurgerDTO;
import saboroso.saborosoburguer.entities.menuItems.MenuItem;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Table
@Getter
@Setter
public class Burger extends MenuItem {
    @ManyToOne
    @JoinColumn (name = "burger_category_id")
    private BurgerCategory burgerCategory;
    @ManyToMany
    @JoinTable(name = "burger_ingredient",
            joinColumns = @JoinColumn(name = "burger_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;
    public Burger(InputBurgerDTO inputBurger) {
        setTitle(inputBurger.title());
        setPrice(inputBurger.price().setScale(2, RoundingMode.HALF_UP));
        setPic(inputBurger.pic());
        setInStock(inputBurger.inStock());
    }

    public void addIngredient(Ingredient ingredient){
        if (this.ingredients == null) this.ingredients = new ArrayList<>();
        this.ingredients.add(ingredient);
    }
    public void removeIngredient(Ingredient ingredient) {
        if (this.ingredients != null) this.ingredients.remove(ingredient);
    }
}