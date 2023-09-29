package saboroso.saborosoburguer.DTO.user;

public record UserUpdatesDTO(
        String password,
        String phoneNumber,
        String name,
        String address
) {}
