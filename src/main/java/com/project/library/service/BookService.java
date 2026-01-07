package com.project.library.service;

import com.project.library.DTO.OpenLibraryDTO;
import com.project.library.exceptions.UserErrorType;
import com.project.library.exceptions.UserValidationException;
import com.project.library.model.FavoriteBook;
import com.project.library.model.User;
import com.project.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.UUID;

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
        if(user == null){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        return user.getBooks();
    }

    public User addBook(String email, OpenLibraryDTO dto){
        var user = userRepository.findUserEmailQuery(email);
        if(user.getUsername() == null){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        FavoriteBook favoriteBook = new FavoriteBook(dto.getTitle(), dto.getImage(),dto.getAuthorName());
        if(user.getBooks().contains(favoriteBook)){
            throw new UserValidationException(UserErrorType.BOOK_ALREADY_EXISTS);
        }
        user.addBook(favoriteBook);
        return userRepository.save(user);
    }

    public void removeBook(String email, UUID id){
        var user = userRepository.findUserEmailQuery(email);
        if(user == null){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        boolean removed = user.getBooks()
                .removeIf(book -> book.getBookID().equals(id));
        if (!removed) {
            throw new UserValidationException(UserErrorType.BOOK_NOT_FOUND);
        }
        userRepository.save(user);
    }
}
