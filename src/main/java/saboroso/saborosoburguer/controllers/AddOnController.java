package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTO.addOn.AddOnDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.services.AddOnService;

import java.util.List;

@RestController
public class AddOnController extends BaseController {
    private final AddOnService addOnService;
    private AddOnController(AddOnService addOnService) {
        this.addOnService = addOnService;
    }

    @PostMapping (value = "/insert-add-on")
    public ResponseEntity<?> insertAddOn(@RequestBody @Valid AddOnDTO addOnDTO) {
        CRUDResponseMessage addOnStatus = addOnService.createAddOn(addOnDTO);
        if (addOnStatus.worked()) return ResponseEntity.ok(addOnStatus);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(addOnStatus);
    }
    @GetMapping(value = "/get-add-ons")
    public ResponseEntity<?> getAddOns() {
        List<AddOnDTO> addOns = addOnService.getAllAddOns();
        return ResponseEntity.ok().body(addOns);
    }
    @PutMapping(value = "/update-add-on")
    public ResponseEntity<?> updateAddOn(@RequestBody @Valid AddOnDTO addOnDTO) {
        CRUDResponseMessage addOnStatus = addOnService.editAddOn(addOnDTO);

        if (addOnStatus.worked()) return ResponseEntity.ok(addOnStatus);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(addOnStatus);
    }
    @DeleteMapping(value = "/remove-add-on/{identifier}")
    public ResponseEntity<?> updateAddOn(@PathVariable (value = "identifier") String addOnIdentifier) {
        CRUDResponseMessage addOnStatus = addOnService.deleteAddOn(addOnIdentifier);

        if (addOnStatus.worked()) return ResponseEntity.ok(addOnStatus);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(addOnStatus);
    }

    @GetMapping(value = "/get-add-ons-for-menu")
    public ResponseEntity<?> getAddOnsForMenu() {
        List<AddOnDTO> addOns = addOnService.getAllAddOns();
        return ResponseEntity.ok().body(addOns);
    }
}
