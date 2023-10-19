package saboroso.saborosoburguer.entities.menuItems.burger;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table
public class BurgerCategory extends BurgerComponent{

  public BurgerCategory(String categoryName) {
    setDeleted(false);
    setTitle(categoryName);
  }

}
