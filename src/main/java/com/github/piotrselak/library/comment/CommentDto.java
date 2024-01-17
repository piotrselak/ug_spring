package com.github.piotrselak.library.comment;

import lombok.Data;

@Data
public class CommentDto {
    private String text;
    private String author;

    public static CommentDto fromEntity(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setText(comment.getText());

        if (comment.getAuthor() == null) commentDto.setAuthor("Anonymous");
        else commentDto.setAuthor(comment.getAuthor().getName());
        return commentDto;
    }
}
