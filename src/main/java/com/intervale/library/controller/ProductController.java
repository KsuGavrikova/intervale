package com.intervale.library.controller;

import com.intervale.library.dto.ProductDto;
import com.intervale.library.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/book/")
    public List<ProductDto> findAllBook() {
        return productService.getBook();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable("id") @Positive Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Positive Long id) {
        if (productService.deleteByID(id)) {
            log.info("IN delete - Author with id " + id + "was deleted");
        } else {
            log.warn("IN delete - Author with id " + id + "was not deleted");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
