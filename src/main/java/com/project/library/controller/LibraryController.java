package com.project.library.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class LibraryController {

    @GetMapping("/")
    public void getLibraries(){}

    @GetMapping("/{id}")
    public void getLibrary(){}

    @PutMapping("/{id}")
    public void updateLibrary(){}

    @DeleteMapping("{id}")
    public void removeLibrary(){}

    @GetMapping("/test")
    public Map<String, Object> test(){
        return Map.of(
                "status", "OK",
                "version", 1,
                "mensagem", "Olá, mundo");
    }
}
