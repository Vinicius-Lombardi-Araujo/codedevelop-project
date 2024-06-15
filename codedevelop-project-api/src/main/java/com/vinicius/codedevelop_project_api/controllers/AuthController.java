package com.vinicius.codedevelop_project_api.controllers;

import com.vinicius.codedevelop_project_api.domain.dtos.LoginResponseDTO;
import com.vinicius.codedevelop_project_api.domain.dtos.TokenDTO;
import com.vinicius.codedevelop_project_api.domain.dtos.LoginRequestDTO;
import com.vinicius.codedevelop_project_api.domain.dtos.UserResponseDTO;
import com.vinicius.codedevelop_project_api.domain.entities.User;
import com.vinicius.codedevelop_project_api.repositories.UserRepository;
import com.vinicius.codedevelop_project_api.services.TokenService;
import com.vinicius.codedevelop_project_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          TokenService tokenService,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequestDTO data) throws Exception {
        User user = userRepository.findByEmail(data.email()).orElseThrow(Exception::new);
        if(passwordEncoder.matches(data.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getType().getType(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}