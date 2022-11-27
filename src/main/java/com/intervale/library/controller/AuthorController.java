package com.intervale.library.controller;

import com.intervale.library.dto.AuthorDto;
import com.intervale.library.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/author")
@Slf4j
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/")
    public List<AuthorDto> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public AuthorDto getById(@PathVariable("id") @Positive Long id) {
        return authorService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDto> createBook(@RequestBody @Validated AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.create(authorDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateBook(@PathVariable @Positive Long id, @RequestBody AuthorDto authorDto) {
        authorDto.setId(id);
        return ResponseEntity.ok(authorService.update(authorDto));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Positive Long id) {
        if (authorService.deleteByID(id)) {
            log.info("IN delete - Author with id " + id + "was deleted");
        } else {
            log.warn("IN delete - Author with id " + id + "was not deleted");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
