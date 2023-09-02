package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import saboroso.saborosoburguer.DTOs.LoginDTO;
import saboroso.saborosoburguer.DTOs.TokenDTO;
import saboroso.saborosoburguer.entities.UserEntity;
import saboroso.saborosoburguer.model.BaseController;
import saboroso.saborosoburguer.security.Token;

@RestController
public class AuthenticationController extends BaseController {
    private final AuthenticationManager authenticationManager;
    private final Token tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, Token token) {
        this.authenticationManager = authenticationManager;
        tokenService = token;
    }
    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO login) {
        UsernamePasswordAuthenticationToken username = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        Authentication auth = authenticationManager.authenticate(username);
        String token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
