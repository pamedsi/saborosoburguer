package saboroso.saborosoburguer.DTOs.burger;

import java.util.List;
import java.util.Map;

public record MenuBurgersDTO(Map<String, List<BurgerDTO>> categories) {}
