package com.pyc.java.board.boundedContext.article.repository;

import com.pyc.java.board.boundedContext.article.dto.Article;
import com.pyc.java.board.util.Util;

import java.util.ArrayList;
import java.util.List;
public class ArticleRepository {
    private List<Article> articles;
    private int lastId;
    public ArticleRepository(){
        articles = new ArrayList<>();
        lastId = 0;

    }

    public int write(String subject, String content,String writerName){
        int id = ++lastId;

        Article article = new Article(id,subject,content,writerName);
        articles.add(article);
        return id;
    }

    public List<Article> findAll(String orderBy){
        List<Article> sortedArticles = articles;

        if(orderBy.equals("idAsc")){
            return articles;
        }

        if(orderBy.equals("idDesc")){
            sortedArticles = Util.reverseList(articles);
        }
        return sortedArticles;
    }

    public List<Article> findAll(String searchKeyWord, String orderBy) {
        List<Article> filteredArticles = findAll(orderBy);

        if(!searchKeyWord.trim().isEmpty()){
            filteredArticles = new ArrayList<>();

            articles.stream()
                    .filter(article-> article.getSubject().contains(searchKeyWord) ||
                            article.getContent().contains(searchKeyWord))
                    .forEach(filteredArticles::add);
        }
        return filteredArticles;
    }

    public Article findById(int id){
        return articles.stream()
                .filter(article-> article.getId()==id)
                .findFirst()
                .orElse(null);
    }


    public void modify(int id, String subject, String content) {
        Article article = findById(id);

        if(article == null){
            return;
        }

        article.setSubject(subject);
        article.setContent(content);
    }

    public void delete(int id) {
        Article article = findById(id);

        if(article == null){
            return;
        }

        articles.remove(article);
    }
}
