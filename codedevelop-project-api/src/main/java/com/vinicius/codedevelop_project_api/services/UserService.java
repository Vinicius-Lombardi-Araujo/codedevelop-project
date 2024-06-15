package com.vinicius.codedevelop_project_api.services;

import com.vinicius.codedevelop_project_api.domain.entities.User;
import com.vinicius.codedevelop_project_api.domain.enums.UserType;
import com.vinicius.codedevelop_project_api.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User insert(User user) throws Exception {
        if(loadUserByUsername(user.getUsername()) != null)
            throw new Exception("Usuario ja existe");

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        User newUser = new User(user.getName(), user.getEmail(), encryptedPassword, UserType.USER);

        return userRepository.save(newUser);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(Exception::new);
    }

    public User update(User user) throws Exception {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(Exception::new);

        if(!user.getName().isEmpty()) existingUser.setName(user.getName());
        if(!user.getEmail().isEmpty()) existingUser.setEmail(user.getEmail());
        if(!user.getPassword().isEmpty()) existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(existingUser);
    }

    public void delete(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(Exception::new);
        userRepository.delete(user);
    }
}
