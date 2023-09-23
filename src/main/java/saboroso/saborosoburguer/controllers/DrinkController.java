package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTOs.drink.DrinkDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.models.Message;
import saboroso.saborosoburguer.services.DrinkService;
import saboroso.saborosoburguer.DTOs.drink.InputDrinkDTO;

import java.util.List;

@RestController
public class DrinkController extends BaseController {
    private final DrinkService drinkService;
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }
    @PostMapping(value = "/save-drink")
    public ResponseEntity<?> saveDrink(@RequestBody @Valid InputDrinkDTO drinkDTO){
        if (drinkService.createDrink(drinkDTO)) return ResponseEntity.ok(new Message(drinkDTO.title() + " cadastrado.", null));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(drinkDTO.title() + " já cadastrado.", null));
    }
    @GetMapping(value = "/get-drinks")
    public ResponseEntity<?> getDrinks(){
        List<DrinkDTO> drinks = this.drinkService.getAllDrinks();
        return ResponseEntity.ok(drinks);
    }
    @GetMapping(value = "/get-drinks-for-menu")
    public ResponseEntity<?> getDrinksForMenu(){
        return null;
    }
    @DeleteMapping(value = "/remove-drink/{identifier}")
    public ResponseEntity<?> removeDrink(@PathVariable (value = "identifier") String drinkIdentifier){
        CRUDResponseMessage drinkStatus = this.drinkService.deleteDrink(drinkIdentifier);
        if (drinkStatus.worked()) return ResponseEntity.ok(drinkStatus);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(drinkStatus);
    }
}
