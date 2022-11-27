package com.intervale.library.mapper;

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
        uses = {AuthorMapper.class},
        componentModel = "spring")
public interface ProductMapper {
    ProductDto entityToDto(Product entity);

    Product dtoToEntity(ProductDto dto);

    List<ProductDto> mapBooksToBooksDto(List<Product> products);

    List<AuthorInfoDto> map(List<Author> authors);
}
