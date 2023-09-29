package saboroso.saborosoburguer.DTO.order;
public record ItemAndQuantityDTO(
        String identifier,
        Long quantity,
        Boolean isCombo
) {}
