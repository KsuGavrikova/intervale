package com.intervale.library.service;

import com.intervale.library.dto.AuthorDto;
import com.intervale.library.mapper.AuthorMapper;
import com.intervale.library.model.Author;
import com.intervale.library.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorDto> findAll() {
        try {
            List<Author> authors = authorRepository.findAll();
            log.info("IN getAll - Authors was found");
            if (authors.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                return authors.stream()
                        .map(authorMapper::entityToDto)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error("IN getAll - authors Repository Exception " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public AuthorDto findById(Long authorId) {
        try {
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            log.info("IN getById - Author with id " + authorId + " was found");

            return authorMapper.entityToDto(author);

        } catch (Exception e) {
            log.error("IN getById - authors Repository Exception " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public AuthorDto create(AuthorDto authorDto) {
        Author author = authorMapper.dtoToEntity(authorDto);
        return authorMapper.entityToDto(authorRepository.save(author));
    }

    public AuthorDto update(AuthorDto authorDto) {
        if (authorRepository.existsById(authorDto.getId())) {
            log.info("IN update - Author for update was found");
            Author author = authorMapper.dtoToEntity(authorDto);
            return authorMapper.entityToDto(authorRepository.save(author));
        } else {
            log.info("IN update - Author for update not found" + authorDto);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public boolean deleteByID(Long authorId) {
        try {
            authorRepository.deleteById(authorId);
            return true;
        } catch (Exception e) {
            log.warn("IN delete - Author with id {} was not delete. Exception message: {}", authorId, e.getMessage());
            return false;
        }
    }
}
