package com.project.library.controller;

import com.project.library.DTO.OpenLibraryDTO;
import com.project.library.service.OpenLibraryService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Victoria
 */
@RequestMapping("api/v1")
@RestController
public class OpenLibraryController {

    private OpenLibraryService service;

    public OpenLibraryController(OpenLibraryService service){
        this.service = service;
    }

    @GetMapping("/book")
    public OpenLibraryDTO getBook(@RequestParam String q){
        return service.getBook(q);
    }
}
