package saboroso.saborosoburguer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saboroso.saborosoburguer.services.MenuService;

@RestController
@CrossOrigin(origins = "*")
public class MenuController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    @GetMapping
    @RequestMapping(value = "/get-menu")
    public ResponseEntity<?> seeBurgersForMenu() {
        return ResponseEntity.ok(menuService.getMenuItems());
    }
}
