package saboroso.saborosoburguer.DTOs.drink;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.Drink;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DrinkMapper {
    public DrinkForMenuDTO singleDrinkForMenuMapperDTO (Drink drink){
        return new DrinkForMenuDTO(drink.getIdentifier(), drink.getTitle(), drink.getMl(), drink.getPrice());
    }
    public List<DrinkForMenuDTO> drinkForMenuMapper(List<Drink> drinks){
        return drinks.stream().map(this::singleDrinkForMenuMapperDTO).collect(Collectors.toList());
    };
}
