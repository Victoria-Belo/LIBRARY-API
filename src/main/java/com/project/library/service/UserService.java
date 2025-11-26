package com.project.library.service;
import com.project.library.exceptions.UserErrorType;
import com.project.library.exceptions.UserValidationException;
import com.project.library.interfaces.UserInterface;
import com.project.library.model.User;
import com.project.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
    public User createUser(User user) throws NoSuchAlgorithmException {
        if(repository.findByEmail(user.getEmail())){
            throw new UserValidationException(UserErrorType.USER_TAKEN);
        }
        String crypto = hashing(user.getPassword());
        return repository.save(user);
    }

    @Override
    public void remove(long id) {
        if(!repository.existsById(id)){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        repository.deleteById(id);
    }

    @Override
    public User update(long id, User user) {
        if(!repository.existsById(id)){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        return repository.save(user);
    }

    @Override
    public String hashing(String password) throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = algorithm.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }
}
