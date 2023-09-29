package saboroso.saborosoburguer.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @Email
        String login,
        @NotBlank
        String password
) {}
