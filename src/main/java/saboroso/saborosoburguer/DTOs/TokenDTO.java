package saboroso.saborosoburguer.DTOs;

import saboroso.saborosoburguer.models.UserRole;

public record TokenDTO(
        String token,
        UserRole role
) {}
