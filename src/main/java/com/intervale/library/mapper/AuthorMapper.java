package com.intervale.library.mapper;

import com.intervale.library.dto.AuthorDto;
import com.intervale.library.dto.ProductDto;
import com.intervale.library.dto.projection.AuthorInfoDto;
import com.intervale.library.model.Author;
import com.intervale.library.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface AuthorMapper {
    AuthorDto entityToDto(Author entity);

    Author dtoToEntity(AuthorDto dto);

    Author nameDtoToEntity(AuthorInfoDto entity);

    AuthorInfoDto entityToInfoDto(Author entity);

    List<ProductDto> map(List<Product> products);
}

