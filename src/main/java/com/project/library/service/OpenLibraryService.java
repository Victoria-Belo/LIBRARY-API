package com.project.library.service;

import com.project.library.DTO.OpenLibraryDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Victoria
 */
@Service
public class OpenLibraryService {

    private WebClient webclient;


    OpenLibraryService(WebClient webclient){
        this.webclient = webclient;
    }

    public OpenLibraryDTO getBook(String q){
        final String uri = "/search.json";
        JsonNode client = webclient.get().uri(uriBuilder -> uriBuilder
                .path(uri)
                .queryParam("q", q)
                .build()
        )
                .retrieve()
                .onStatus( status -> !status.is2xxSuccessful(),
                        response -> Mono.error(new Throwable("Problema com API externa"))
                                )
                .bodyToMono(JsonNode.class)
                .block();

        System.out.println("->" + client);
        JsonNode authorsNode = client.get("docs").get(0).get("author_name");

        List<String> authors = new ArrayList<>();

        if (authorsNode != null && authorsNode.isArray()) {
            for (JsonNode author : authorsNode) {
                authors.add(author.asText());
            }
        }
        String cover_i = client.get("docs").get(0).get("cover_i").asText();
        String title = client.get("docs").get(0).get("title").asText();
        String image ="https://covers.openlibrary.org/b/id/" + cover_i + "-M.jpg";


       OpenLibraryDTO response = new OpenLibraryDTO(authors, title, image);

       System.out.println(response);
       return response;
    }


}
