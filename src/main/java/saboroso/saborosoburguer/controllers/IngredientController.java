package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import saboroso.saborosoburguer.DTOs.ingredient.inputIngredientDTO;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.IngredientService;

@RestController
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @PostMapping
    @RequestMapping (value = "/insert-ingredient")
    public ResponseEntity<?> addIngredient(@Valid @RequestBody inputIngredientDTO ingredientDTO) {
        if (ingredientService.insertIngredient(ingredientDTO)) return ResponseEntity.ok(new Message(ingredientDTO.title() + " adicionado!"));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(ingredientDTO.title() + " já adicionado."));
    }
    @GetMapping
    @RequestMapping (value = "/menu-ingredients-management")
    public ResponseEntity<?> getIngredientsForMenuManagement() {
        return ResponseEntity.ok(ingredientService.getIngredientsForMenuManagement());
    }
    @PostMapping
    @RequestMapping (value = "/update-ingredient")
    public ResponseEntity<?> updateIngredient(@Valid @RequestBody inputIngredientDTO changes, @PathVariable String identifier) {
        if (ingredientService.editIngredient(changes, identifier)) return ResponseEntity.ok(new Message(changes.title() + " atualizado!"));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(changes.title() + " já tem esses dados."));
    }
    @GetMapping
    @RequestMapping (value = "/menu-ingredients")
    public ResponseEntity<?> getMenuIngredients() {
        return ResponseEntity.ok(ingredientService.getIngredientsForMenu());
    }
}
