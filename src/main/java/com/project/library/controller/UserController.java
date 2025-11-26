package com.project.library.controller;
import com.project.library.model.User;
import com.project.library.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        return userService.user(id);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.users();
    }

    @DeleteMapping("user/{id}")
    public User removeUser(@PathVariable Long id){
        userService.remove(id);
        return null;
    }

    @PutMapping("user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.update(id, user);
    }
}
