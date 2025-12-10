package com.project.library.controller;

import com.project.library.DTO.LoginResponseDTO;
import com.project.library.DTO.UserAuthenticationDTO;
import com.project.library.DTO.UserDTO;
import com.project.library.DTO.UserDTOValidation;
import com.project.library.config.security.TokenService;
import com.project.library.model.User;
import com.project.library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/id/{id}")
    public User getUser(@PathVariable Long id){
        return userService.user(id);
    }


    @GetMapping("/list")
    public List<User> getUsers(){
        return userService.users();
    }

    @PostMapping("/register")
    public User createUser(
            @Validated(UserDTOValidation.Create.class)
            @RequestBody UserDTO dto
    ) {
        return userService.createUser(dto);
    }

    @DeleteMapping("/delete/{id}")
    public User removeUser(@PathVariable Long id){
        userService.remove(id);
        return null;
    }

    @PatchMapping("/email/{id}")
    public User updateEmail(
            @PathVariable Long id,
            @Validated(UserDTOValidation.UpdateEmail.class)
            @RequestBody UserDTO email
    ) {
        return userService.update(id, email);
    }

    @PatchMapping("/password/{id}")
    public User updatePassword(
            @PathVariable Long id,
            @Validated(UserDTOValidation.UpdatePassword.class)
            @RequestBody UserDTO password
    ) {
        return userService.updatePassword(id, password);
    }

    public User updateAuth(@PathVariable Long id, @RequestBody UserAuthenticationDTO dto){
        return userService.updateAuth(id, dto);
    }

    @PostMapping("/login")
    public ResponseEntity loginRequest(@Valid @RequestBody UserAuthenticationDTO dto){
       var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
       var auth = this.authenticationManager.authenticate(usernamePassword);

       var token = tokenService.generateToken((User) Objects.requireNonNull(auth.getPrincipal()));
       System.out.println("usernamePassword " + usernamePassword + "\nauth " + auth + "\ntoken " + token);
       return ResponseEntity.ok(token);
    }
}
