package com.jhonathan.loja3d.controller;

import com.jhonathan.loja3d.dto.AuthenticationDTO;
import com.jhonathan.loja3d.dto.LoginResponseDTO;
import com.jhonathan.loja3d.dto.RegisterDTO;
import com.jhonathan.loja3d.domain.Auth.User;
import com.jhonathan.loja3d.infra.security.TokenService;
import com.jhonathan.loja3d.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO dto){
        if (this.repository.findByEmail(dto.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encriptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        var newUser = new User(dto.name(), dto.email(), encriptedPassword, dto.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
