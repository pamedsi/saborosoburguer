package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.DrinkService;
import saboroso.saborosoburguer.DTOs.drink.InputDrinkDTO;

@RestController
@CrossOrigin(origins = "*")
public class DrinkController {
    private final DrinkService drinkService;
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }
    @PostMapping
    @RequestMapping (value = "/save-drink")
    public ResponseEntity<?> saveDrink(@RequestBody @Valid InputDrinkDTO drinkDTO){
        if (drinkService.createDrink(drinkDTO)) return ResponseEntity.ok(new Message(drinkDTO.title() + " cadastrado."));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(drinkDTO.title() + " já cadastrado."));
    }
}
