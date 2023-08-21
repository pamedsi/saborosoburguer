package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
public class ClientOrder {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column
    @Setter (AccessLevel.NONE)
    private String identifier;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity clientWhoOrdered;
    @Column
    @Setter (AccessLevel.NONE)
    private LocalDateTime timeOfPurchase;

}
