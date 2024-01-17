package com.github.piotrselak.library.book.domain;

import lombok.Data;

@Data
public class SearchParams {
    String title;
    String authors;
    String genres;
}
