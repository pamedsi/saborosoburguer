package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;

import saboroso.saborosoburguer.DTO.drink.DrinkDTO;
import saboroso.saborosoburguer.DTO.drink.DrinkMapper;
import saboroso.saborosoburguer.DTO.drink.InputDrinkDTO;
import saboroso.saborosoburguer.entities.Drink;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.repositories.DrinkRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public CRUDResponseMessage editDrink(DrinkDTO changes) {
        if (changes == null) return new CRUDResponseMessage(false, "Nenhuma alteração fornecida!", null);
        Boolean alreadyExists = drinkRepository.existsByTitleAndMlAndIdentifierNotAndDeletedFalse(changes.title(), changes.ml(), changes.identifier());
        if (alreadyExists) return new CRUDResponseMessage(false, "Já existe uma bebida com esse nome!", null);
        Drink drinkToEdit = drinkRepository.findByIdentifierAndDeletedFalse(changes.identifier());
        if (drinkToEdit == null) return new CRUDResponseMessage(false, "Bebida não encontrada!", null);
        
        List<String> changesForResponse = new ArrayList<>();

        if (!Objects.equals(drinkToEdit.getTitle(), changes.title())) {
            drinkToEdit.setTitle(changes.title());
            changesForResponse.add("Título modificado! Agora se chama: " + changes.title());
        }
        if (drinkToEdit.getPrice().compareTo(changes.price()) != 0) {
            drinkToEdit.setPrice(changes.price());
            changesForResponse.add("Preço alterado! Agora custa: " + changes.price());
        }
        if (drinkToEdit.getMl().compareTo(changes.ml()) != 0) {
            drinkToEdit.setMl(changes.ml());
            changesForResponse.add("mL alterado! Agora é: " + changes.ml());
        }
        if (drinkToEdit.getInStock() != changes.inStock()) {
            drinkToEdit.setInStock(changes.inStock());
            if (changes.inStock()) changesForResponse.add(changes.title() + " disponibilizado!");
            else changesForResponse.add(changes.title() + " indisponibilizado!");
        }

        if (changesForResponse.isEmpty()) return new CRUDResponseMessage(false, "Nenhuma mudança solicitada é diferente dos dados já presentes!", null);
        drinkRepository.save(drinkToEdit);
        return new CRUDResponseMessage(true, null, changesForResponse);
    }

    public List<DrinkDTO> getDrinksForMenu () {
        List<Drink> drinksPersistence = drinkRepository.findAllByDeletedFalseAndInStockTrue();
        return drinkMapper.severalToDTO(drinksPersistence);
    }
}
