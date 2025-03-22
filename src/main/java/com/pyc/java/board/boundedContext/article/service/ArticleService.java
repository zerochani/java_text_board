package com.pyc.java.board.boundedContext.article.service;

import com.pyc.java.board.boundedContext.article.dto.Article;
import com.pyc.java.board.boundedContext.article.repository.ArticleRepository;
import com.pyc.java.board.container.Container;

import java.util.List;
import java.util.stream.IntStream;

public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(){
        articleRepository = Container.articleRepository;

    }

    public int write(String subject, String content, String writerName, int memberId, int boardId){
        return articleRepository.write(subject, content, writerName, memberId, boardId);
    }

    public List<Article> findAll(String searchKeyWord,String searchKeyWordTypeCode, String orderBy, int boardId){
        return articleRepository.findAll(searchKeyWord,searchKeyWordTypeCode, orderBy, boardId);
    }

    public Article findById(int id){
        return articleRepository.findById(id);
    }

    public void modify(int id, String subject, String content){
        articleRepository.modify(id, subject, content);
    }

    public void delete(int id){
        articleRepository.delete(id);
    }
}
