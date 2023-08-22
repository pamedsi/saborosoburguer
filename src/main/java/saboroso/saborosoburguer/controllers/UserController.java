package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTOs.user.InputUserDTO;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping (value = "/create-user")
    public ResponseEntity<?> addNewUser(@RequestBody @Valid InputUserDTO userDTO) {
        userService.addUser(userDTO);
        return ResponseEntity.ok(new Message(userDTO.name() + " cadastrado."));
    }

}
