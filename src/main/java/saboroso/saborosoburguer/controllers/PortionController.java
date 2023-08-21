package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saboroso.saborosoburguer.DTOs.portion.InputPortionDTO;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.PortionService;

@RestController
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