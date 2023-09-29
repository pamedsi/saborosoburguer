package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTO.burger.BurgerBreadDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.services.BreadService;

import java.util.List;

@RestController
public class BreadController extends BaseController {

    private final BreadService breadService;

    public BreadController(BreadService breadService) {
        this.breadService = breadService;
    }

    @PostMapping("/insert-bread")
    public ResponseEntity<?> insertBread(@RequestBody @Valid BurgerBreadDTO breadDTO) {
        CRUDResponseMessage breadStatus = breadService.createBread(breadDTO);
        if (breadStatus.worked()) return ResponseEntity.ok(breadStatus);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(breadStatus);
    }

    @GetMapping("/get-breads")
    public ResponseEntity<?> getBreads() {
        List<BurgerBreadDTO> breads = breadService.getAllBreads();
        return ResponseEntity.ok().body(breads);
    }

    @PutMapping("/update-bread")
    public ResponseEntity<?> updateBread(@RequestBody @Valid BurgerBreadDTO breadDTO) {
        CRUDResponseMessage breadStatus = breadService.editBread(breadDTO);

        if (breadStatus.worked()) return ResponseEntity.ok(breadStatus);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(breadStatus);
    }

    @DeleteMapping("/remove-bread/{identifier}")
    public ResponseEntity<?> deleteBread(@PathVariable ("identifier") String breadIdentifier) {
        CRUDResponseMessage breadStatus = breadService.deleteBread(breadIdentifier);

        if (breadStatus.worked()) return ResponseEntity.ok(breadStatus);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(breadStatus);
    }

    @GetMapping("/get-breads-for-menu")
    public ResponseEntity<?> getBreadsForMenu() {
        List<BurgerBreadDTO> breads = breadService.getBreadsForMenu();
        return ResponseEntity.ok().body(breads);
    }
}
