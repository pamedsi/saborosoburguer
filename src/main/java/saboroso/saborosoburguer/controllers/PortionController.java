package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTOs.portion.InputPortionDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.Message;
import saboroso.saborosoburguer.services.PortionService;

@RestController
public class PortionController extends BaseController {
    private final PortionService portionService;
    public PortionController(PortionService portionService) {
        this.portionService = portionService;
    }
    @PostMapping (value = "/save-portion")
    public ResponseEntity<?> addPortion(@RequestBody @Valid InputPortionDTO portionDTO) {
        portionService.createPortion(portionDTO);
        return ResponseEntity.ok(new Message(portionDTO.title() + " cadastrado!", null));
    }
}