package com.vinicius.codedevelop_project_api.controllers;

import com.vinicius.codedevelop_project_api.domain.dtos.UserRegisterDTO;
import com.vinicius.codedevelop_project_api.domain.dtos.UserResponseDTO;
import com.vinicius.codedevelop_project_api.domain.entities.User;
import com.vinicius.codedevelop_project_api.domain.enums.UserType;
import com.vinicius.codedevelop_project_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService
                .findAll()
                .stream()
                .map(user -> new UserResponseDTO(user.getName(), user.getEmail(), user.getType().getType()))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) throws Exception {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserResponseDTO(user.getName(), user.getEmail(), user.getType().getType()));
    }


    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid UserRegisterDTO data) throws Exception {
        userService.insert(new User(data.name(), data.email(), data.password(), UserType.valueOf(data.type())));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UserRegisterDTO data) throws Exception {
        User updatedUser = userService.update(new User(id, data.name(), data.email(), data.password(), UserType.valueOf(data.type())));
        return ResponseEntity.ok().body(new UserResponseDTO(updatedUser.getName(), updatedUser.getEmail(), updatedUser.getType().getType()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}