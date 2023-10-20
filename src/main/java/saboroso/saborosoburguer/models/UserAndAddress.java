package saboroso.saborosoburguer.models;

import saboroso.saborosoburguer.entities.Address;
import saboroso.saborosoburguer.entities.UserEntity;

public record UserAndAddress(
        UserEntity user,
        Address address
) {}
