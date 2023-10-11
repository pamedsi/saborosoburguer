package saboroso.saborosoburguer.services;

import jakarta.ws.rs.NotFoundException;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.burger.*;
import saboroso.saborosoburguer.DTO.ingredient.IngredientDTO;
import saboroso.saborosoburguer.entities.burger.Burger;
import saboroso.saborosoburguer.entities.burger.BurgerCategory;
import saboroso.saborosoburguer.entities.burger.Ingredient;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.repositories.BurgerRepository;
import saboroso.saborosoburguer.repositories.BurgerSaleRepository;
import saboroso.saborosoburguer.repositories.CategoryRepository;
import saboroso.saborosoburguer.repositories.IngredientRepository;

import java.util.*;

@Service
public class BurgerService {
    private final BurgerRepository burgerRepository;
    private final IngredientRepository ingredientRepository;
    private final BurgerMapper burgerMapper;
    private final BurgerSaleRepository burgerSaleRepository;
    private final CategoryRepository categoryRepository;
    private BurgerService(BurgerRepository burgerRepository, IngredientRepository ingredientRepository, BurgerMapper burgerMapper, BurgerSaleRepository burgerSaleRepository, CategoryRepository categoryRepository){
        this.burgerRepository = burgerRepository;
        this.ingredientRepository = ingredientRepository;
        this.burgerMapper = burgerMapper;
        this.burgerSaleRepository = burgerSaleRepository;
        this.categoryRepository = categoryRepository;
    }
    public CRUDResponseMessage createBurger (InputBurgerDTO burgerDTO) {
        System.out.println(burgerDTO);
        if (burgerRepository.existsBurgerByTitleAndDeletedFalse(burgerDTO.title()))
            return new CRUDResponseMessage(false, "Já existe um hambúrguer com esse nome!", null);
        BurgerCategory category = categoryRepository.findByIdentifier(burgerDTO.categoryDTO().identifier());
        if (category == null)
            return new CRUDResponseMessage(false, "Categoria não encontrada!", null);
        List<Ingredient> ingredients = burgerDTO.ingredients().stream().map(ingredient -> {
            Ingredient foundIngredient = ingredientRepository.findByIdentifierAndDeletedFalse(ingredient.identifier());
            if (foundIngredient == null) throw new NotFoundException(ingredient.title() + " não encontrado!");
            return foundIngredient;
        }).toList();
        Burger newBurger = new Burger(burgerDTO);
        newBurger.setBurgerCategory(category);
        newBurger.setIngredients(ingredients);
        burgerRepository.save(newBurger);
        return new CRUDResponseMessage(true, null, null);
    }
    public Map<String, List<BurgerDTO>> getMenuBurgers () {
        List<Burger> availableBurgers = burgerRepository.findBurgerByDeletedFalseAndInStockTrue();
        Map<String, List<BurgerDTO>> burgersSortedByCategories = new HashMap<>();

        for (Burger burger : availableBurgers) {
            String category = burger.getBurgerCategory().getTitle();
            if (!burgersSortedByCategories.containsKey(category)) burgersSortedByCategories.put(category, new ArrayList<BurgerDTO>());
            burgersSortedByCategories.get(category).add(burgerMapper.singleToDTO(burger));
        }
        return burgersSortedByCategories;
    }
    public List<BurgerDTO> getBurgersForMenuManagement() {
        List<Burger> burgers = burgerRepository.findByDeletedFalse();
        return burgerMapper.severalToDTO(burgers);
    }
    public CRUDResponseMessage updateBurger(BurgerDTO changes) {
        if (changes == null) return new CRUDResponseMessage(false, "Nenhuma alteração fornecida!", null);

        Boolean alreadyExists = burgerRepository.existsByTitleAndIdentifierNotAndDeletedFalse(changes.title(), changes.identifier());
        if (alreadyExists) return new CRUDResponseMessage(false, "Já existe um hambúrguer com esse nome!", null);
        Burger burgerToEdit = burgerRepository.findByIdentifierAndDeletedFalse(changes.identifier());
        if (burgerToEdit == null) return new CRUDResponseMessage(false, "Hambúrguer não encontrado!", null);

        List<String> changesForResponse = new ArrayList<>();

        if (!Objects.equals(burgerToEdit.getTitle(), changes.title())) {
            burgerToEdit.setTitle(changes.title());
            changesForResponse.add("Título modificado! Agora se chama: " + changes.title());
        }
        if (!Objects.equals(burgerToEdit.getBurgerCategory().getIdentifier(), changes.categoryDTO().identifier())) {
            BurgerCategory comingCategory = categoryRepository.findByIdentifier(changes.categoryDTO().identifier());
            if(comingCategory == null) return new CRUDResponseMessage(false, "Categoria não encontrada", null);
            burgerToEdit.setBurgerCategory(comingCategory);
            changesForResponse.add("Categoria atualizada! Agora é: " + changes.categoryDTO().title());
        }
        if (burgerToEdit.getPrice().compareTo(changes.price()) != 0) {
            burgerToEdit.setPrice(changes.price());
            changesForResponse.add("Preço alterado! Agora custa: " + changes.price());
        }
        if (!Objects.equals(burgerToEdit.getPic(), changes.pic())) {
            burgerToEdit.setPic(changes.pic());
            changesForResponse.add("Foto atualizada! Nova URL: " + changes.pic());
        }
        if (burgerToEdit.getInStock() != changes.inStock()) {
            burgerToEdit.setInStock(changes.inStock());
            if (changes.inStock()) changesForResponse.add(changes.title() + " disponibilizado!");
            else changesForResponse.add(changes.title() + " indisponibilizado!");
        }
        // Inserindo ingredientes novos:
        for (IngredientDTO ingredientComing : changes.ingredients()) {
            boolean alreadyOnIt = burgerToEdit.getIngredients().stream()
                    .anyMatch(ingredientOnBurger -> ingredientOnBurger.getIdentifier().equals(ingredientComing.identifier()));
            if (!alreadyOnIt) {
                Ingredient ingredientToAdd = ingredientRepository.findByIdentifierAndDeletedFalse(ingredientComing.identifier());
                if (ingredientToAdd == null) {
                    return new CRUDResponseMessage(false, "Ingrediente não encontrado!", null);
                }
                burgerToEdit.addIngredient(ingredientToAdd);
                changesForResponse.add(ingredientComing.title() + " inserido!");
            }
        }
        // Removendo ingredientes que não vieram na requisição:
        List<Ingredient> ingredientsToRemove = new ArrayList<>();
        burgerToEdit.getIngredients().forEach(ingredientOnBurger -> {
            boolean stillPresent = changes.ingredients().stream()
                    .anyMatch(ingredientComing -> ingredientComing.identifier().equals(ingredientOnBurger.getIdentifier()));
            if (!stillPresent) {
                ingredientsToRemove.add(ingredientOnBurger);
                changesForResponse.add(ingredientOnBurger.getTitle() + " removido!");
            }
        });
        ingredientsToRemove.forEach(burgerToEdit::removeIngredient);

        if (changesForResponse.isEmpty()) return new CRUDResponseMessage(false, "Nenhuma mudança solicitada é diferente dos dados já presentes!", null);

        burgerRepository.save(burgerToEdit);
        return new CRUDResponseMessage(true, null, changesForResponse);
    }
    
    public MostSoldBurgersDTO getHighLightBurgers() {
        try {
            // BurgersIdsAndAmounts burgersIdsAndAmounts = new BurgersIdsAndAmounts(burgerSaleRepository.getMostSold());
            // Burger burger1Persistence = burgerRepository.findSingleById(burgersIdsAndAmounts.firstBurgerId);
            // Burger burger2Persistence = burgerRepository.findSingleById(burgersIdsAndAmounts.secondBurgerId);
            // List<BurgerDTO> burgersDTO = burgerMapper.severalToDTO(Arrays.asList(burger1Persistence, burger2Persistence));
//            SoldBurgerDTO burger1 = new SoldBurgerDTO(burgersDTO.get(0), burgersIdsAndAmounts.firstBurgerAmount);
//            SoldBurgerDTO burger2 = new SoldBurgerDTO(burgersDTO.get(1), burgersIdsAndAmounts.secondBurgerAmount);
//            return new MostSoldBurgersDTO(burger1, burger2);
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }
    public Boolean addIngredientToBurger (String burgerIdentifier, String ingredientIdentifier) {
        Burger burger = burgerRepository.findBurgerByIdentifier(burgerIdentifier);
        if (burgerRepository.hasIngredient(burger, ingredientIdentifier)) return false;
        burger.getIngredients().add(ingredientRepository.findByIdentifierAndDeletedFalse(ingredientIdentifier));
        burgerRepository.save(burger);
        return true;
    }
    public Boolean removeIngredient(String burgerIdentifier, String ingredientIdentifier) {
        Burger burger = burgerRepository.findBurgerByIdentifier(burgerIdentifier);
        if (!burgerRepository.hasIngredient(burger, ingredientIdentifier)) return false;
        burger.getIngredients().remove(ingredientRepository.findByIdentifierAndDeletedFalse(ingredientIdentifier));
        burgerRepository.save(burger);
        return true;
    }
    public CRUDResponseMessage deleteBurger(String burgerIdentifier) {
        if (burgerIdentifier.length() != 36) return new CRUDResponseMessage(false, "Identificador inválido!", null);
        Burger burger = burgerRepository.findBurgerByIdentifier(burgerIdentifier);
        if (burger == null) return new CRUDResponseMessage(false, "Hambúrguer não encontrado!", null);
        if (burger.getDeleted()) return new CRUDResponseMessage(false, "Hambúrguer já deletado!", null);

        burger.setDeleted(true);
        burgerRepository.save(burger);
        return new CRUDResponseMessage(true, null, List.of(burger.getTitle()  + "deletado!"));
    }
}
