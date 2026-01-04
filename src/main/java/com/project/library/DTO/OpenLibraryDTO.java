package com.project.library.DTO;

import java.util.List;

/**
 * @author Victoria
U    */
public class OpenLibraryDTO {

    private List<String> authorName;
    private String title;
    private String image;

    public OpenLibraryDTO(){}

    public OpenLibraryDTO(List<String> authorName,String title, String image){
        this.authorName = authorName;
        this.title=title;
        this.image = image;
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
    public String toString() {
        return "OpenLibraryDTO{" +
                "authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
