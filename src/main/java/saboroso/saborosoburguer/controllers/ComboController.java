package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTO.combo.ComboDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.services.ComboService;

@RestController
public class ComboController extends BaseController {
    private final ComboService comboService;

    public ComboController (ComboService comboService) {
        this.comboService = comboService;
    }

    @PostMapping (value = "/add-combo")
    public ResponseEntity<?> addCombo(@RequestBody ComboDTO comboDTO){
        CRUDResponseMessage comboStatus = comboService.createCombo(comboDTO);
        if (comboStatus.worked()) return ResponseEntity.ok(comboStatus);
        return ResponseEntity.badRequest().body(comboStatus);
    }
    @GetMapping(value = "/get-all-combos")
    public ResponseEntity<?> getAllCombos(){
        return ResponseEntity.ok(comboService.getCombos());
    }
    @PutMapping(value = "/update-combo")
    public ResponseEntity<?> updateCombo(@RequestBody @Valid ComboDTO comboDTO){
        CRUDResponseMessage comboStatus = comboService.editCombo(comboDTO);
        if (comboStatus.worked()) return ResponseEntity.ok(comboStatus);
        return ResponseEntity.badRequest().body(comboStatus);
    }
    @DeleteMapping(value = "/remove-combo/{identifier}")
    public ResponseEntity<?> removeCombo(@PathVariable (value = "identifier") String comboIdentifier){
        CRUDResponseMessage comboStatus = comboService.deleteCombo(comboIdentifier);
        if (comboStatus.worked()) return ResponseEntity.ok(comboStatus);
        return ResponseEntity.badRequest().body(comboStatus);
    }
}
