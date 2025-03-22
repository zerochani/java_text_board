package com.pyc.java.board.boundedContext.article.dto;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString

public class Article {
    private final int id;
    private String subject;
    private String content;
    private String writerName;

}
