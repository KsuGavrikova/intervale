package com.intervale.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "publishing_house")
    private String publishingHouse;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "genre")
    private String genre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_authors",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;
}
