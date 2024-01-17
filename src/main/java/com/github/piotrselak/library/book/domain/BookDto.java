package com.github.piotrselak.library.book.domain;

import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String imageUrl;
    private String description;
    private LocalDate releaseDate;
    private int rate; // Sum of stars
    private int votes;
    private String publishingHouse;
    private Set<Genre> genres;
    private Set<Author> authors;
    private String stars;

    public static BookDto fromEntity(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setImageUrl(book.getImageUrl());
        bookDto.setDescription(book.getDescription());
        bookDto.setReleaseDate(book.getReleaseDate());
        bookDto.setRate(book.getRate());
        bookDto.setVotes(book.getVotes());
        bookDto.setPublishingHouse(book.getPublishingHouse());
        bookDto.setGenres(book.getGenres());
        bookDto.setAuthors(book.getAuthors());

        StringBuilder stars = new StringBuilder();
        int rating = book.getRate() / book.getVotes();

        stars.append("★".repeat(Math.max(0, rating)));

        bookDto.setStars(stars.toString());
        return bookDto;
    }
}
