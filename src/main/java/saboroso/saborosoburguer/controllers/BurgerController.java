package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import saboroso.saborosoburguer.DTO.burger.BurgerDTO;
import saboroso.saborosoburguer.DTO.burger.InputBurgerDTO;
import saboroso.saborosoburguer.DTO.burger.MostSoldBurgersDTO;
import saboroso.saborosoburguer.DTO.ingredient.IngredientIdentifierDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.models.Message;
import saboroso.saborosoburguer.services.BurgerService;

import java.util.List;

@RestController
public class BurgerController extends BaseController {
    private final BurgerService burgerService;
    private BurgerController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }
    @PostMapping (value = "/save-burger")
    public ResponseEntity<?> insertBurger(@RequestBody @Valid InputBurgerDTO burgerDTO) {
        CRUDResponseMessage burgerStatus = burgerService.createBurger(burgerDTO);
        if (burgerStatus.worked()) return ResponseEntity.ok(new Message("Hambúrguer cadastrado!", null));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(burgerStatus.reasonWhy(), null));
    }
    @GetMapping(value = "/highlight-burgers")
    public ResponseEntity<?> seeHighLightBurgers() {
        MostSoldBurgersDTO mostSoldBurgersDTO = burgerService.getHighLightBurgers();
        if (mostSoldBurgersDTO == null) return ResponseEntity.ok(List.of());
        return ResponseEntity.ok(mostSoldBurgersDTO);
    }
    @GetMapping(value = "/get-burgers-for-menu")
    public ResponseEntity<?> getBurgersForMenu() {
        return ResponseEntity.ok(burgerService.getMenuBurgers());
    }
    @GetMapping(value = "/burgers-management")
    public ResponseEntity<?> getBurgersForManagement() {
        return ResponseEntity.ok(burgerService.getBurgersForMenuManagement());
    }
    @PutMapping (value = "/add-ingredient/{burgerIdentifier}")
    public ResponseEntity<?> addIngredient(@PathVariable("burgerIdentifier") String burgerIdentifier,
            @RequestBody IngredientIdentifierDTO ingredientIdentifierDTO) {
        if (burgerService.addIngredientToBurger(burgerIdentifier, ingredientIdentifierDTO.ingredientIdentifier())) {
            return ResponseEntity.ok(new Message("Ingrediente adicionado!", null));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message("Ingrediente já adicionado!", null));
    }
    @PutMapping (value = "/update-burger")
    public ResponseEntity<?> editBurger(@RequestBody BurgerDTO burgerDTO) {
        CRUDResponseMessage burgerStatus = burgerService.updateBurger(burgerDTO);
        if (burgerStatus.worked()) return ResponseEntity.ok(new Message("Atualização feita com sucesso!", burgerStatus.changes()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Não foi possível continuar. " + burgerStatus.reasonWhy(), null));
    }
    @DeleteMapping (value = "/remove-burger-ingredient/{burgerIdentifier}")
    public ResponseEntity<?> removeIngredient(@PathVariable("burgerIdentifier") String burgerIdentifier,
            @RequestBody IngredientIdentifierDTO ingredientIdentifierDTO) {
        if (burgerService.removeIngredient(burgerIdentifier, ingredientIdentifierDTO.ingredientIdentifier())) {
            return ResponseEntity.ok(new Message("Ingrediente removido!", null));
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Message("Ingrediente já removido!", null));
    }
    @DeleteMapping (value = "/delete-burger/{burgerIdentifier}")
    public ResponseEntity<?> removeIngredient(@PathVariable("burgerIdentifier") String burgerIdentifier) {
        CRUDResponseMessage burgerStatus = burgerService.deleteBurger(burgerIdentifier);
        if (burgerStatus.worked()) return ResponseEntity.ok(burgerStatus);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(burgerStatus);
    }
}
