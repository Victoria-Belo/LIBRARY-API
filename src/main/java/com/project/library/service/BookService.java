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

    public void removeBook(String email, String title){
        var user = userRepository.findUserEmailQuery(email);
        if(user == null){
            throw new UserValidationException(UserErrorType.USER_NOT_FOUND);
        }
        if(title == null || title.isEmpty()){
            throw new UserValidationException(UserErrorType.TITLE_IS_EMPTY);
        }
        boolean removed = user.getBooks()
                .removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        if (!removed) {
            throw new UserValidationException(UserErrorType.BOOK_NOT_FOUND);
        }
        userRepository.save(user);
    }
}
