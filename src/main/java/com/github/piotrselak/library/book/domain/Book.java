package com.github.piotrselak.library.book.domain;

import com.github.piotrselak.library.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    private String title;
    private String imageUrl;
    private String description;
    private LocalDate releaseDate;
    private int rate; // Sum of stars
    private int votes;
    private String publishingHouse;
    @ManyToMany
    private Set<Genre> genres;
    @ManyToMany
    private Set<Author> authors;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>();
}
