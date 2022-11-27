package com.intervale.library.service;

import com.intervale.library.dto.ProductDto;
import com.intervale.library.mapper.ProductMapper;
import com.intervale.library.model.Product;
import com.intervale.library.repository.ProductRepository;
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
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> findAll() {
        try {
            List<Product> products = productRepository.findAll();
            log.info("IN getAll - products was found");
            if (products.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                return products.stream()
                        .map(productMapper::entityToDto)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error("IN getAll - products Repository Exception " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<ProductDto> getBook() {
        return findAll().stream()
                .filter(p -> p.getType().equals("книга"))
                .collect(Collectors.toList());
    }

    public ProductDto findById(Long authorId) {
        try {
            Product product = productRepository.findById(authorId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            log.info("IN getById - product with id " + authorId + " was found");

            return productMapper.entityToDto(product);

        } catch (Exception e) {
            log.error("IN getById - product Repository Exception " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean deleteByID(Long productId) {
        try {
            productRepository.deleteById(productId);
            log.info("IN delete - product with id {} was delete", productId);
            return true;
        } catch (Exception e) {
            log.warn("IN delete - product with id {} was not delete. Exception message: {}", productId, e.getMessage());
            return false;
        }
    }
}
