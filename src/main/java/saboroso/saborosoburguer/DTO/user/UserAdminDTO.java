package saboroso.saborosoburguer.DTO.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserAdminDTO(
        @NotBlank
        String name,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String address,
        String password,
        @Email
        String email
) {}
