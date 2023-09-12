package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Table
public class BurgerCategory {
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  @Getter
  private String identifier;
  @Column
  @Getter
  @Setter
  private String title;
  @Column
  @Getter
  @Setter
  private Boolean deleted;
  public BurgerCategory(String categoryName) {
    this.deleted = false;
    identifier = UUID.randomUUID().toString();
    title = categoryName;
  }
    public BurgerCategory(String categoryName, Boolean deleted) {
    this.deleted = deleted;
    identifier = UUID.randomUUID().toString();
    title = categoryName;
  }
}
