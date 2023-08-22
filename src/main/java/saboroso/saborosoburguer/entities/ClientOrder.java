package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@Data
public class ClientOrder {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column
    @Setter (AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity clientWhoOrdered;
    @Column
    @Setter (AccessLevel.NONE)
    private LocalDateTime timeOfPurchase = LocalDateTime.now();
    @Column
    @Setter (AccessLevel.NONE)
    private BigDecimal total;

    public ClientOrder(UserEntity buyer, BigDecimal price) {
        clientWhoOrdered = buyer;
        total = price.setScale(2, RoundingMode.HALF_UP);
    }
}
