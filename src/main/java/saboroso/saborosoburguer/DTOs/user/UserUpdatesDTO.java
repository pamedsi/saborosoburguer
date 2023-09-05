package saboroso.saborosoburguer.DTOs.user;

public record UserUpdatesDTO(
        String password,
        String phoneNumber,
        String name,
        String address
) {}
