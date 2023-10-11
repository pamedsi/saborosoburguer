package saboroso.saborosoburguer.DTO.user;

import java.util.List;

public record UserClientDTO(
        String name,
        String phoneNumber,
        String address,
        List<String> addresses
) {}
