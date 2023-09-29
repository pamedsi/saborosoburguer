package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTO.portion.InputPortionDTO;
import saboroso.saborosoburguer.DTO.portion.PortionDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.models.Message;
import saboroso.saborosoburguer.services.PortionService;

import java.util.List;

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
    @GetMapping (value = "/get-all-portions")
    public ResponseEntity<?> getAllPortions() {
        List<PortionDTO> allPortions = portionService.getAllPortions();
        return ResponseEntity.ok(allPortions);
    }
    @DeleteMapping (value = "/remove-portion/{identifier}")
    public ResponseEntity<?> deletePortion(@PathVariable (value = "identifier") String portionIdentifier) {
        CRUDResponseMessage portionStatus = portionService.removePortion(portionIdentifier);
        if (portionStatus.worked()) return ResponseEntity.ok(new Message(portionStatus.changes().get(0), null));

        return ResponseEntity.badRequest().body(new Message(portionStatus.reasonWhy(), null));
    }
    @PutMapping (value = "/update-portion")
    public ResponseEntity<?> updatePortion(@RequestBody PortionDTO portionDTO) {
        CRUDResponseMessage portionStatus = portionService.editPortion(portionDTO);
        if (portionStatus.worked()) return ResponseEntity.ok().body(new Message("Porção atualizada!", portionStatus.changes()));
        return ResponseEntity.badRequest().body(new Message(portionStatus.reasonWhy(), null));
    }
}