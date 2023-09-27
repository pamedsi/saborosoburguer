package saboroso.saborosoburguer.controllers;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
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
import saboroso.saborosoburguer.models.BaseController;
import saboroso.saborosoburguer.models.Message;
import saboroso.saborosoburguer.models.UserRole;
import saboroso.saborosoburguer.repositories.UserRepository;
import saboroso.saborosoburguer.security.Token;

import java.util.Objects;

@RestController
public class AuthenticationController extends BaseController {
    private final AuthenticationManager authenticationManager;
    private final Token tokenService;
    private final UserRepository userRepository;
    public AuthenticationController(AuthenticationManager authenticationManager, Token token, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        tokenService = token;
        this.userRepository = userRepository;
    }
    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken username = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.password());
        Authentication auth = authenticationManager.authenticate(username);
        UserEntity user = (UserEntity) (UserEntity) auth.getPrincipal();
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new TokenDTO(token, user.getRole()));
    }
    @PostMapping ("/admin-token")
    public ResponseEntity<?> validateAdminToken(@RequestBody TokenDTO tokenDTO) {
        String userEmail = tokenService.validateToken(tokenDTO.token());
        if (Objects.equals(userEmail, "")) return ResponseEntity.status(403).body(new Message("Token inválido!", null));
        UserEntity user = userRepository.findByEmail(userEmail);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Usuário não encontrado!", null));
        if (user.getRole() != UserRole.ADMIN) return ResponseEntity.status(403).body(new Message("Usuário não autorizado!", null));

        return ResponseEntity.ok(new TokenDTO(userEmail, UserRole.ADMIN));
    }
}
