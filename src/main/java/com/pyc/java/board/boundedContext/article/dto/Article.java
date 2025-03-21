package com.pyc.java.board.boundedContext.article.dto;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString

public class Article {
    private final int id;
    private String regDate;
    private String updateDate;
    private String subject;
    private String content;
    private String writerName; //작성자명
    private int memberId;
    private int boardId;

}
