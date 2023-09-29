package saboroso.saborosoburguer.DTO;

import saboroso.saborosoburguer.models.UserRole;

public record TokenDTO(
        String token,
        UserRole role
) {}
