package com.project.library.interfaces;

import com.project.library.DTO.UserDTO;
import com.project.library.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserInterface {

    User user(long id);
    List<User> users();
    User createUser(UserDTO user);
    void remove(long id);
    User update(long id, UserDTO user);

}
