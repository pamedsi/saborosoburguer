package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import saboroso.saborosoburguer.DTOs.LoginDTO;
import saboroso.saborosoburguer.model.BaseController;
import saboroso.saborosoburguer.model.Message;

@RestController
public class AuthenticationController extends BaseController {
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO login) {
        UsernamePasswordAuthenticationToken username = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        authenticationManager.authenticate(username);

        return ResponseEntity.ok(new Message("Bem-vindo!"));
    }
}
