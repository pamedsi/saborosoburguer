package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saboroso.saborosoburguer.DTOs.user.InputUserDTO;
import saboroso.saborosoburguer.model.Message;
import saboroso.saborosoburguer.services.UserService;

@RestController
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
