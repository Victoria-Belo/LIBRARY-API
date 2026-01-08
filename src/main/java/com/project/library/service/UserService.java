package com.project.library.service;
import com.project.library.DTO.UserAuthenticationDTO;
import com.project.library.DTO.UserDTO;
import com.project.library.exceptions.UserErrorType;
import com.project.library.exceptions.UserValidationException;
import com.project.library.interfaces.UserInterface;
import com.project.library.model.User;
import com.project.library.model.UserRole;
import com.project.library.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements UserInterface {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
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
        System.out.println("dto: " + dto);
        if( repository.existsByEmail(dto.getEmail())){
            throw new UserValidationException(UserErrorType.USER_TAKEN);
        }
        String crypto = hashing(dto.getPassword());
        dto.setPassword(crypto);
        User user = new User(dto.getEmail(), dto.getPassword());

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
    public User update(long id, UserDTO dto) {
        if(!repository.existsById(id)){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        User user = repository.findById(id);
        if(!dto.getEmail().equals(user.getEmail())){
            user.setEmail(dto.getEmail());
        }
        return repository.save(user);
    }

    public User updatePassword(long id, UserDTO dto){
        if(!repository.existsById(id)){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        User user = repository.findById(id);
        String crypto = hashing(dto.getPassword());
        user.setPassword(crypto);
        return repository.save(user);
    }

    public User updateAuth(long id, UserAuthenticationDTO dto){
        if(!repository.existsById(id)){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        User user = repository.findById(id);
        if(dto.getAuth().equalsIgnoreCase("admin")){
            user.setRole(UserRole.ADMIN);
        }else{
            user.setRole(UserRole.USER);
        }
        return repository.save(user);
    }

    public String hashing(String password) {
        return encoder.encode(password);
    }

    public boolean verifyPassword(String raw, String hashed) {
        return encoder.matches(raw, hashed);
    }

    public UserDetails loginRequest(UserAuthenticationDTO dto){
        if(!repository.existsByEmail(dto.getEmail())){
            throw new UserValidationException(UserErrorType.EMAIL_NOT_FOUND);
        }
        UserDetails user = repository.findByEmail(dto.getEmail());
        if(!verifyPassword(dto.getPassword(), user.getPassword())){
            throw new UserValidationException(UserErrorType.INVALID_PASSWORD);
        }
        return user;
    }

}
