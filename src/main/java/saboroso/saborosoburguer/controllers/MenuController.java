package saboroso.saborosoburguer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.services.MenuService;

@RestController
public class MenuController extends BaseController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    @GetMapping(value = "/get-menu")
    public ResponseEntity<?> seeBurgersForMenu() {
        return ResponseEntity.ok(menuService.getMenuItems());
    }
}
