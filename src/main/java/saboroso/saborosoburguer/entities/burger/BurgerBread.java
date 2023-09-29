package saboroso.saborosoburguer.entities.burger;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import saboroso.saborosoburguer.DTO.burger.BurgerBreadDTO;

@Entity
@Table
@NoArgsConstructor
public class BurgerBread extends BurgerComponent{
    public BurgerBread(BurgerBreadDTO burgerBreadDTO) {
        setTitle(burgerBreadDTO.title());
        setInStock(burgerBreadDTO.inStock());
    }
}
