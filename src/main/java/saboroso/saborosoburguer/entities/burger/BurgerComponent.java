package saboroso.saborosoburguer.entities.burger;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class BurgerComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Long id;
    @Setter(AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
    @Column(nullable = false)
    private String title;
    @Column
    private Boolean inStock;
    @Column
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt = LocalDateTime.now();;
    @Column
    private Boolean deleted = false;
    @Column
    private LocalDateTime lastEdited;
}
