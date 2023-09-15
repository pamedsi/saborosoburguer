package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import saboroso.saborosoburguer.DTOs.ingredient.IngredientDTO;
import saboroso.saborosoburguer.model.BaseController;
import saboroso.saborosoburguer.model.IngredientResponseMessage;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.IngredientService;

@RestController
public class IngredientController extends BaseController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @PostMapping(value = "/insert-ingredient")
    public ResponseEntity<?> addIngredient(@Valid @RequestBody IngredientDTO ingredientDTO) {
        if (ingredientService.insertIngredient(ingredientDTO)) return ResponseEntity.status(HttpStatus.CREATED).body((new Message(ingredientDTO.title() + " adicionado!")));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(ingredientDTO.title() + " já adicionado."));
    }
    @GetMapping(value = "/menu-ingredients-management")
    public ResponseEntity<?> getIngredientsForMenuManagement() {
        return ResponseEntity.ok(ingredientService.getIngredientsForMenuManagement());
    }
    @PutMapping(value = "/update-ingredient")
    public ResponseEntity<?> updateIngredient(@Valid @RequestBody IngredientDTO changes) {
        IngredientResponseMessage ingredientStatus = ingredientService.editIngredient(changes);
        if (ingredientStatus.worked()) return ResponseEntity.ok(new Message(changes.title() + " atualizado!"));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(ingredientStatus.reason()));
    }
    @GetMapping(value = "/menu-ingredients")
    public ResponseEntity<?> getMenuIngredients() {
        return ResponseEntity.ok(ingredientService.getIngredientsForMenu());
    }
    @DeleteMapping(value = "/remove-ingredient/{identifier}")
    public ResponseEntity<?> deleteIngredient(@PathVariable String identifier) {
        if (ingredientService.removeIngredient(identifier)) return ResponseEntity.ok(new Message("Ingrediente deletado!"));
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body((new Message("Este ingrediente não existe ou já foi deletado!")));
    }    
}
