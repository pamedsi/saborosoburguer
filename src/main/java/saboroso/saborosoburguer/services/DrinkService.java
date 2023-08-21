package saboroso.saborosoburguer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.drink.InputDrinkDTO;
import saboroso.saborosoburguer.entities.Drink;
import saboroso.saborosoburguer.repositories.DrinkRepository;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public Boolean createDrink(InputDrinkDTO inputDrinkDTO) {
        if (drinkRepository.existsByTitleAndMl(inputDrinkDTO.title(), inputDrinkDTO.ml())) return false;
        Drink newDrink = new Drink(inputDrinkDTO);
        drinkRepository.save(newDrink);
        return true;
    }
}
