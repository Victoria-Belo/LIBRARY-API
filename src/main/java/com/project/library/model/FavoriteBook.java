package com.project.library.model;

import jakarta.persistence.Embeddable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Victoria
 * A persistência de objetos de valor (Value Objects) envolve o armazenamento de seus atributos
 * dentro da entidade que os compõe, sem a necessidade de um identificador próprio,
 * pois eles não possuem identidade independente no sistema.
 */
@Embeddable
public class FavoriteBook {

    private final UUID bookID;
    private List<String> authorName;

    private String title;
    private String image;

    protected FavoriteBook() {
        this.bookID = UUID.randomUUID();;
    }

    public FavoriteBook(String title, String image, List<String> authorName){
        this.authorName = authorName;
        this.image = image;
        this.title = title;
        this.bookID = UUID.randomUUID();
    }

    public UUID getBookID() {
        return bookID;
    }

    public List<String> getAuthorName() {
        return authorName;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteBook book)) return false;
        return title.equals(book.title)
                && authorName.equals(book.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authorName);
    }
}
