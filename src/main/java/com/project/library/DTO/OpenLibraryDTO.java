package com.project.library.DTO;

/**
 * @author Victoria
 *  image  cover_edition_key($key = olid | $Size: S, M, L).
 *  https://covers.openlibrary.org/b/$key/$value-$size.jpg
 *  Exemplo https://covers.openlibrary.org/b/olid/OL51694024M-M.jpg
 */
public class OpenLibraryDTO {

    private String authorName;
    private String title;
    private String image;
    private int totalPages;

    OpenLibraryDTO(){}

    public String getAuthorName() {
        return authorName;
    }

       public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }


    public int getTotalPages() {
        return totalPages;
    }

}
