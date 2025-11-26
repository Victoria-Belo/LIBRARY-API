package com.project.library.interfaces;

import com.project.library.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserInterface {

    public User user(long id);
    public List<User> users();
    public User createUser(User user) throws NoSuchAlgorithmException;
    public void remove(long id);
    public User update(long id, User user);
    public String hashing(String password) throws NoSuchAlgorithmException;

}
