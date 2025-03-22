package com.pyc.java.board.boundedContext.article.repository;

import com.pyc.java.board.boundedContext.article.dto.Article;
import com.pyc.java.board.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
    private List<Article> articles;
    private int lastId;
    public ArticleRepository(){
        articles = new ArrayList<>();
        lastId = 0;

        makeArticleTestData();
    }

    private void makeArticleTestData() {
        IntStream.rangeClosed(1,100)
                .forEach(i->write("제목" + i, "내용" + i, "홍길동", 1,
                        (int)(Math.random() * 2) + 1));
    }


    public int write(String subject, String content,String writerName, int memberId, int boardId){
        int id = ++lastId;

        String regDate = Util.getNowDateStr();
        String updateDate = Util.getNowDateStr();

        Article article = new Article(id,regDate, updateDate, subject,content,writerName, memberId, boardId);
        articles.add(article);
        return id;
    }

    //정렬 게시물 리스트
    public List<Article> findByOrderByIdDesc(String orderBy){
        List<Article> sortedArticles = articles;

        if(orderBy.equals("idAsc")){
            return articles;
        }

        if(orderBy.equals("idDesc")){
            sortedArticles = Util.reverseList(articles);
        }
        return sortedArticles;
    }

    //전체 게시물 리스트 가져오는 리스트
    public List<Article> findAll(String searchKeyWord, String orderBy, int boardId) {
        List<Article> filteredArticles = findByOrderByIdDesc(orderBy);

        if(boardId == 0){
            return filteredArticles;
        }
        //boardId에 맞는 게시물 필터링
        if(boardId > 0){
            return filteredArticles.stream()
                    .filter(article->article.getBoardId()==boardId)
                    .toList();
        }

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
