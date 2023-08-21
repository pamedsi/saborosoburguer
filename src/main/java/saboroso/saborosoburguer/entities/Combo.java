package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;

@Entity
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column (unique = true)
    @Setter (AccessLevel.NONE)
    private String identifier;
}
