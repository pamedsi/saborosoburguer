package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private UserEntity belongsTo;
    @Column
    private String content;

    public Address(UserEntity userOwner, String address){
        this.belongsTo = userOwner;
        this.content = address;
    }
}