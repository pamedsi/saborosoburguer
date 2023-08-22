package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTOs.portion.InputPortionDTO;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.PortionService;

@RestController
@CrossOrigin(origins = "*")
public class PortionController {
    private final PortionService portionService;
    public PortionController(PortionService portionService) {
        this.portionService = portionService;
    }
    @PostMapping
    @RequestMapping(value = "/save-portion")
    public ResponseEntity<?> addPortion(@RequestBody @Valid InputPortionDTO portionDTO) {
        portionService.createPortion(portionDTO);
        return ResponseEntity.ok(new Message(portionDTO.title() + " cadastrado!"));
    }
}