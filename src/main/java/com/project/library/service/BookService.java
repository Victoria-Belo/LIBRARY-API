package com.project.library.service;

import com.project.library.DTO.OpenLibraryDTO;
import com.project.library.exceptions.UserErrorType;
import com.project.library.exceptions.UserValidationException;
import com.project.library.model.FavoriteBook;
import com.project.library.model.User;
import com.project.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Victoria
 */

@Service
public class BookService {

    private final UserRepository userRepository;

    BookService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<FavoriteBook> allBooks(String email){
        var user = userRepository.findUserEmailQuery(email);
        if(user.getUsername() == null){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        return user.getBooks();
    }

    public User addBook(String email, OpenLibraryDTO dto){
        var user = userRepository.findUserEmailQuery(email);
        if(user.getUsername() == null){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        FavoriteBook favoriteBook = new FavoriteBook( dto.getTitle(), dto.getImage(),dto.getAuthorName());
        user.addBook(favoriteBook);
        userRepository.save(user);
        return user;
    }
}
