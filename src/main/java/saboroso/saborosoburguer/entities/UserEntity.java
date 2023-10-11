package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import saboroso.saborosoburguer.DTO.user.UserAdminDTO;
import saboroso.saborosoburguer.DTO.user.UserClientDTO;
import saboroso.saborosoburguer.models.UserRole;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static saboroso.saborosoburguer.security.Password.hash;

@Entity
@Data
@Table
@NoArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private Long id;
    @Column
    @Setter (AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
    @Column
    private UserRole role = UserRole.CUSTOMER;
    @Column (nullable = false)
    private String name;
    @Column
    private String phoneNumber;
    @Column (unique = true)
    private String email;
    @Column
    private String passwordHash;
    @Column
    private LocalDateTime userSince = LocalDateTime.now();
    public UserEntity(UserAdminDTO userDTO) {
        name = userDTO.name();
        phoneNumber = userDTO.phoneNumber();
        passwordHash = hash(userDTO.password());
        email = userDTO.email();
    }
    public UserEntity(UserClientDTO userDTO) {
        name = userDTO.name();
        phoneNumber = userDTO.phoneNumber();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        return List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}