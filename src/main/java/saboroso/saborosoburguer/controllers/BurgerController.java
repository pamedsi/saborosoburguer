package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTOs.burger.InputBurgerDTO;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientIdentifierDTO;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.BurgerService;

@RestController
public class BurgerController {
    private final BurgerService burgerService;
    private BurgerController (BurgerService burgerService) {
        this.burgerService = burgerService;
    }
    @PostMapping
    @RequestMapping (value = "save-burger")
    public ResponseEntity<?> insertBurger (@RequestBody @Valid InputBurgerDTO burgerDTO) {
        if (burgerService.createBurger(burgerDTO)) return ResponseEntity.ok(new Message("Hambúrguer cadastrado!"));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message("Hambúrguer já cadastrado!"));
    }
    @GetMapping
    @RequestMapping (value = "get-menu-burgers")
    public ResponseEntity<?> seeBurgersForMenu () {
        return ResponseEntity.ok(burgerService.getMenuBurgers());
    }
    @PutMapping
    @RequestMapping (value = "/add-ingredient/{burgerIdentifier}")
    public ResponseEntity<?> addIngredient(@PathVariable("burgerIdentifier") String burgerIdentifier, @RequestBody IngredientIdentifierDTO ingredientIdentifierDTO) {
        if (burgerService.addIngredientToBurger(burgerIdentifier, ingredientIdentifierDTO.ingredientIdentifier())) {
            return ResponseEntity.ok(new Message("Ingrediente adicionado!"));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message("Ingrediente já adicionado!"));
    }
    @PutMapping
    @RequestMapping (value = "/add-photo/{burgerIdentifier}")
    public ResponseEntity<?> addPhoto(@PathVariable("burgerIdentifier") String burgerIdentifier, @RequestBody InputBurgerDTO ingredientIdentifierDTO) {
        if (burgerService.addPhotoToBurger(burgerIdentifier, ingredientIdentifierDTO.pic())) {
            return ResponseEntity.ok(new Message("Foto adicionada!"));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message("Não foi possível adicionar a foto!"));
    }
    @DeleteMapping
    @RequestMapping (value = "/remove-ingredient/{burgerIdentifier}")
    public ResponseEntity<?> removeIngredient(@PathVariable("burgerIdentifier") String burgerIdentifier, @RequestBody IngredientIdentifierDTO ingredientIdentifierDTO) {
        if (burgerService.removeIngredient(burgerIdentifier, ingredientIdentifierDTO.ingredientIdentifier())) {
            return ResponseEntity.ok(new Message("Ingrediente removido!"));
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Message("Ingrediente já removido!"));
    }


}