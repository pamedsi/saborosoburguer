package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saboroso.saborosoburguer.DTOs.user.InputUserDTO;
import saboroso.saborosoburguer.model.UserRole;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private Long id;
    @Column
    @Setter (AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
    @Column
    private UserRole role = UserRole.CLIENT;
    @Column
    private String name;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private LocalDateTime userSince = LocalDateTime.now();
    @OneToMany(mappedBy = "clientWhoOrdered")
    private List<ClientOrder> orders;

    public UserEntity(InputUserDTO userDTO) {
        name = userDTO.name();
        phoneNumber = userDTO.phoneNumber();
        address = userDTO.address();
    }
}