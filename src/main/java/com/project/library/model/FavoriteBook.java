package com.project.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import java.util.List;

/**
 * @author Victoria
 * A persistência de objetos de valor (Value Objects) envolve o armazenamento de seus atributos
 * dentro da entidade que os compõe, sem a necessidade de um identificador próprio,
 * pois eles não possuem identidade independente no sistema.
 */
@Embeddable
public class FavoriteBook {


    private List<String> authorName;

    private String title;
    private String image;

    protected FavoriteBook() {}

    public FavoriteBook(String title, String image, List<String> authorName){
        this.authorName = authorName;
        this.image = image;
        this.title = title;
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
}
