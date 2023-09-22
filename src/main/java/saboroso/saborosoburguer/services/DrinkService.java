package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.address.AddressMapper;
import saboroso.saborosoburguer.DTOs.drink.DrinkDTO;
import saboroso.saborosoburguer.DTOs.drink.DrinkMapper;
import saboroso.saborosoburguer.DTOs.drink.InputDrinkDTO;
import saboroso.saborosoburguer.entities.Drink;
import saboroso.saborosoburguer.repositories.DrinkRepository;

import java.util.List;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;
    private final DrinkMapper drinkMapper;

    public DrinkService(DrinkRepository drinkRepository, DrinkMapper drinkMapper) {
        this.drinkRepository = drinkRepository;
        this.drinkMapper = drinkMapper;
    }

    public Boolean createDrink(InputDrinkDTO inputDrinkDTO) {
        if (drinkRepository.existsByTitleAndMl(inputDrinkDTO.title(), inputDrinkDTO.ml())) return false;
        Drink newDrink = new Drink(inputDrinkDTO);
        drinkRepository.save(newDrink);
        return true;
    }

    public List<DrinkDTO> getAllDrinks() {
        List<Drink> drinksPersistence = drinkRepository.findAllByDeletedFalse();
        return drinkMapper.severalToDTO(drinksPersistence);
    }
}
