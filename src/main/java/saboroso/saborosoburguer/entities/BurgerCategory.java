package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table
@Data
public class BurgerCategory {
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Long id;
  @Column (unique = true)
  @Setter(AccessLevel.NONE)
  private String identifier;
  @Column (unique = true)
  private String title;
  @Column
  private Boolean deleted;
  @Column
  @Setter(AccessLevel.NONE)
  private LocalDateTime createdAt;
  @Column
  private LocalDateTime lastEditedIn;
  public BurgerCategory(String categoryName) {
    this.deleted = false;
    identifier = UUID.randomUUID().toString();
    title = categoryName;
    createdAt = LocalDateTime.now();
  }
  public BurgerCategory(String categoryName, Boolean deleted) {
    this.deleted = deleted;
    identifier = UUID.randomUUID().toString();
    title = categoryName.trim().toUpperCase();
    createdAt = LocalDateTime.now();
  }
}
