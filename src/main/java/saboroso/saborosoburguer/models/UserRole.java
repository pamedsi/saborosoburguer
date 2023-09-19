package saboroso.saborosoburguer.models;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN ("admin"),
    CUSTOMER ("customer");
    private final String role;
    UserRole (String role) {this.role = role;}
}
// Cliente pode:
//     Criar pedido (Order Controller)
//     Criar usuário (se criar)
//     Adicionar endereço à lista de endereços dele mesmo
