package br.com.fiap.meujulius.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.meujulius.models.Credencial;
import br.com.fiap.meujulius.models.Token;
import br.com.fiap.meujulius.models.Usuario;
import br.com.fiap.meujulius.repository.UsuarioRepository;
import br.com.fiap.meujulius.service.TokenService;
import jakarta.validation.Valid;

@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/api/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial) {
        manager.authenticate(credencial.toAuthentication());
        Token token = tokenService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }

}
