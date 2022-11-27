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
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "about")
    private String about;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
//    @JoinTable(name = "product_authors",
//            joinColumns = @JoinColumn(name = "author_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
