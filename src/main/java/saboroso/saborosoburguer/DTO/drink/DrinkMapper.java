package saboroso.saborosoburguer.DTO.drink;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.Drink;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DrinkMapper {
    public DrinkDTO singleToDTO (Drink drink){
        return new DrinkDTO(drink.getIdentifier(), drink.getTitle(), drink.getMl(), drink.getPrice(), drink.getInStock());
    }
    public List<DrinkDTO> severalToDTO(List<Drink> drinks){
        return drinks.stream().map(this::singleToDTO).collect(Collectors.toList());
    };

}
