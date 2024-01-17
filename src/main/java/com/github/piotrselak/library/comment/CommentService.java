package com.github.piotrselak.library.comment;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.book.repository.BookRepository;
import com.github.piotrselak.library.user.domain.User;
import com.github.piotrselak.library.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<CommentDto> findCommentsByBook(Book book) {
        return commentRepository
                .findCommentsByBook(book)
                .stream()
                .map(CommentDto::fromEntity)
                .toList();
    }

    public void addCommentToBook(Long bookId, CommentDto commentDto) {
        User user = null;
        if (!Objects.equals(commentDto.getAuthor(), "anonymousUser"))
            user = userRepository.findByName(commentDto.getAuthor());

        Book book = bookRepository.findById(bookId).get();

        Comment comment = new Comment();
        comment.setBook(book);
        comment.setText(commentDto.getText());
        comment.setAuthor(user);
        commentRepository.save(comment);
    }
}
