package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import saboroso.saborosoburguer.model.UserRole;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private Long id;
    @Column
    @Setter (AccessLevel.NONE)
    private String identifier;
    @Column
    private UserRole role;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private LocalDateTime userSince;
    @OneToMany(mappedBy = "clientWhoOrdered")
    private List<ClientOrder> orders;
}
