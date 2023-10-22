package saboroso.saborosoburguer.entities.menuItems.burger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import saboroso.saborosoburguer.DTO.burger.BurgerBreadDTO;

@Entity
@Table
@NoArgsConstructor
@Getter
public class BurgerBread extends BurgerComponent{
    @Column (length = 2000)
    @Getter
    private String pic;
    public BurgerBread(BurgerBreadDTO burgerBreadDTO) {
        setTitle(burgerBreadDTO.title());
        setInStock(burgerBreadDTO.inStock());
    }
}
