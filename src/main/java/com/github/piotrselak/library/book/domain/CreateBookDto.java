package com.github.piotrselak.library.book.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class CreateBookDto {
    private Long id;
    private String title;
    private String imageUrl;
    private String description;
    private LocalDate releaseDate;
    private int rate;
    private int votes;
    private String publishingHouse;
    private List<String> genres;
    private List<String> authors;

    public static CreateBookDto fromEntity(Book book) {
        CreateBookDto bookDto = new CreateBookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setImageUrl(book.getImageUrl());
        bookDto.setDescription(book.getDescription());
        bookDto.setReleaseDate(book.getReleaseDate());
        bookDto.setRate(book.getRate());
        bookDto.setVotes(book.getVotes());
        bookDto.setPublishingHouse(book.getPublishingHouse());
        var genres = book.getGenres().stream().map(Genre::getName).toList();
        bookDto.setGenres(genres);
        bookDto.setAuthors(book.getAuthors().stream().map(Author::getName).toList());

        return bookDto;
    }
}
