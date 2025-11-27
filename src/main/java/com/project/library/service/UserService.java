package com.project.library.service;
import com.project.library.DTO.UserDTO;
import com.project.library.exceptions.UserErrorType;
import com.project.library.exceptions.UserValidationException;
import com.project.library.interfaces.UserInterface;
import com.project.library.model.User;
import com.project.library.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static java.lang.String.valueOf;

@Service
public class UserService implements UserInterface {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User user(long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> users() {
        return repository.findAll();
    }

    @Override
    public User createUser(UserDTO dto) {
        System.out.println("SERVICE: " + dto.toString());
        if( repository.existsByEmail(dto.getEmail())){
            throw new UserValidationException(UserErrorType.USER_TAKEN);
        }
        String crypto = hashing(dto.getPassword());
        dto.setPassword(crypto);
        User user = new User(dto.getEmail(), dto.getPassword());
        repository.save(user);
        return user;
    }

    @Override
    public void remove(long id) {
        if(!repository.existsById(id)){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        repository.deleteById(id);
    }

    @Override
    public User update(long id, UserDTO dto) {
        if(!repository.existsById(id)){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        User user = repository.findById(id);
        if(!dto.getEmail().equals(user.getEmail())){
            user.setEmail(dto.getEmail());
        }
        return user;
    }

    public User updatePassword(long id, UserDTO dto){
        if(!repository.existsById(id)){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        User user = repository.findById(id);
        String crypto = hashing(dto.getPassword());
        user.setPassword(crypto);
        return user;
    }

    public String hashing(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public boolean verifyPassword(String raw, String hashed) {
        return new BCryptPasswordEncoder().matches(raw, hashed);
    }
}
