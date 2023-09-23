package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.address.AddressMapper;
import saboroso.saborosoburguer.DTOs.drink.DrinkDTO;
import saboroso.saborosoburguer.DTOs.drink.DrinkMapper;
import saboroso.saborosoburguer.DTOs.drink.InputDrinkDTO;
import saboroso.saborosoburguer.entities.Drink;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
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
    public CRUDResponseMessage deleteDrink(String drinkIdentifier) {
        if (drinkIdentifier == null || drinkIdentifier.length() != 36) return new CRUDResponseMessage(
                false, "Identificador inválido!", null
        );
        Drink drink = drinkRepository.findByIdentifier(drinkIdentifier);
        if (drink == null) return new CRUDResponseMessage(false, "Bebida não encontrada!", null);
        if (drink.getDeleted()) return new CRUDResponseMessage(false, "Bebida já excluída!", null);

        drink.setDeleted(true);
        drinkRepository.save(drink);
        return new CRUDResponseMessage(true, null, List.of("Bebida excluída com sucesso!"));
    }
}
