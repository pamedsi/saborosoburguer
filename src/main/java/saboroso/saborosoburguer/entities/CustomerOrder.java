package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saboroso.saborosoburguer.models.OrderStatus;
import saboroso.saborosoburguer.models.PaymentMethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column
    @Setter (AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
    @Column
    @Setter (AccessLevel.NONE)
    private String orderCode;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity clientWhoOrdered;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Setter (AccessLevel.NONE)
    private ZonedDateTime timeOfPurchase = ZonedDateTime.now(ZoneId.of("GMT-3"));
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column // Caso o método de pagamento escolhido tenha sido "hibrido"
    private String howCustomerPaid;
    @Column
    @Setter (AccessLevel.NONE)
    private BigDecimal total;
    @OneToOne
    @JoinColumn(name = "delivered_address_id", unique = false)
    @Setter (AccessLevel.NONE)
    private Address deliveredAddress;

    public CustomerOrder(String orderCode, OrderStatus status, UserEntity buyer, Address deliveredAddress, BigDecimal totalPaid, PaymentMethod paymentMethod, String howCustomerPaid) {
        this.orderStatus = status;
        this.orderCode = orderCode;
        clientWhoOrdered = buyer;
        this.deliveredAddress = deliveredAddress;
        total = totalPaid.setScale(2, RoundingMode.HALF_UP);
        this.paymentMethod = paymentMethod;
        this.howCustomerPaid = howCustomerPaid;
    }
}
