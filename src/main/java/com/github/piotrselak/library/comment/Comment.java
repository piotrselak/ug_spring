package com.github.piotrselak.library.comment;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    @ManyToOne
    private Book book;
    @ManyToOne
    private User author;
}
