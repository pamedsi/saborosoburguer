package saboroso.saborosoburguer.model;

import saboroso.saborosoburguer.entities.Burger;
import saboroso.saborosoburguer.entities.Drink;
import saboroso.saborosoburguer.entities.Portion;

import java.util.List;

public record Menu(
        List<Burger> burgers,
        List<Portion> portions,
        List<Drink> drinks
) {}
