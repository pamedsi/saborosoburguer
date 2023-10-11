package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saboroso.saborosoburguer.DTO.user.UserAdminDTO;
import saboroso.saborosoburguer.DTO.user.UserClientDTO;
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.models.Message;
import saboroso.saborosoburguer.services.UserService;

@RestController
public class UserController extends BaseController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create-user")
    public ResponseEntity<?> addNewUser(@RequestBody @Valid UserAdminDTO userDTO) {
        if (userService.addUserAdmin(userDTO)) return ResponseEntity.ok(new Message(userDTO.name() + " cadastrado.", null));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message(userDTO.name() + "já cadastrado.", null));
    }
    @GetMapping
    public ResponseEntity<?> getUserAddresses (@PathVariable String userIdentifier) {
        return ResponseEntity.ok(userService.getUserAddresses(userIdentifier));
    }
    @GetMapping ("/get-user-by-phone-number/{phone_number}")
    public ResponseEntity<?> getClientUserByPhoneNumber(@PathVariable("phone_number") String phoneNumber){
        UserClientDTO userClientDTO = userService.getClientUserByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(userClientDTO);
    }
}
