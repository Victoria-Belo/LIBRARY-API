package com.project.library.controller;

import com.project.library.DTO.UserDTO;
import com.project.library.DTO.UserDTOValidation;
import com.project.library.model.User;
import com.project.library.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.user(id);
    }

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.users();
    }

    @PostMapping("/")
    public User createUser(
            @Validated(UserDTOValidation.Create.class)
            @RequestBody UserDTO dto
    ) {
        return userService.createUser(dto);
    }

    @DeleteMapping("/{id}")
    public User removeUser(@PathVariable Long id){
        userService.remove(id);
        return null;
    }

    @PatchMapping("email/{id}")
    public User updateEmail(
            @PathVariable Long id,
            @Validated(UserDTOValidation.UpdateEmail.class)
            @RequestBody UserDTO email
    ) {
        return userService.update(id, email);
    }

    @PatchMapping("password/{id}")
    public User updatePassword(
            @PathVariable Long id,
            @Validated(UserDTOValidation.UpdatePassword.class)
            @RequestBody UserDTO password
    ) {
        return userService.updatePassword(id, password);
    }
}
