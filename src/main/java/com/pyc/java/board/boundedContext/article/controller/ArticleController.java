package com.pyc.java.board.boundedContext.article.controller;

import com.pyc.java.board.base.Rq;
import com.pyc.java.board.boundedContext.article.dto.Article;
import com.pyc.java.board.container.Container;
import com.pyc.java.board.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ArticleController {
    public List<Article> articles = new ArrayList<>();
    public int lastId;
    public ArticleController(){
        articles = new ArrayList<>();
        lastId = 0;

        makeArticleTestData();
    }
    public void makeArticleTestData(){
        IntStream.rangeClosed(1,100)
                .forEach(i->articles.add(new Article(i, "제목" + i, "내용" + i)));
    }

    public void doDelete(Rq rq) {
        if (articles.isEmpty()) {
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return;
        }

        int id = rq.getIntParam("id",0);

        if(id==0){
            System.out.println("올바른 값을 입력해주세요.");
            return;
        }

        Article article = findById(id);

        if(article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        articles.remove(article);

        System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
    }

    public void doModify(Rq rq) {
        if (articles.isEmpty()) {
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return;
        }

        int id = rq.getIntParam("id",0);
        if(id==0){
            System.out.println("올바른 값을 입력해주세요.");
            return;
        }

        Article article = findById(id);

        if(article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("== %d번 게시물 수정 ==\n", article.id);
        System.out.print("제목 : ");
        article.subject = Container.sc.nextLine();

        System.out.print("내용 : ");
        article.content = Container.sc.nextLine();

        System.out.printf("%d번 게시물이 수정되었습니다.\n", article.id);
    }

    public void doWrite() {
        lastId = articles.get(articles.size() - 1).id;

        System.out.println("== 게시물 작성 ==");

        System.out.print("제목 : ");
        String subject = Container.sc.nextLine();

        System.out.print("내용 : ");
        String content = Container.sc.nextLine();

        int id = ++lastId;

        Article article = new Article(id, subject, content);

        articles.add(article);

        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
    }

    public void showDetail(Rq rq) {
        if (articles.isEmpty()) {
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return;
        }
        int id = rq.getIntParam("id",0);

        if(id==0){
            System.out.println("올바른 값을 입력해주세요.");
            return;
        }

        Article article = findById(id);

        if(article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.println("== 게시물 상세보기 ==");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.subject);
        System.out.printf("내용 : %s\n", article.content);
    }

    public void showList(Rq rq) {
        Map<String, String> params = rq.getParams();

        if (articles.isEmpty()) {
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return; // 함수는 리턴을 만나면 그 즉시 종료
        }

        // 검색 기능 시작
        List<Article> filteredArticles = articles;

        String searchKeyWord = rq.getParam("searchKeyWord","");

        if (!searchKeyWord.trim().isEmpty()) {
            filteredArticles = new ArrayList<>();

            articles.stream()
                    .filter(article
                            -> article.subject.contains(searchKeyWord) || article.content.contains(searchKeyWord)
                    )
                    .forEach(filteredArticles::add); // article -> articles.add(article)
        }

        // 검색 기능 끝

        // 정렬 로직 시작
        String orderBy = rq.getParam("orderBy", "idDesc");
        boolean orderByIdDesc = orderBy.equals("idDesc");
        if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
            orderByIdDesc = false;
        }

        List<Article> sortedArticles = filteredArticles;

        // orderByIdDesc : true면 내림차순 정렬, 그렇지 않으면 오름차순 정렬
        if (orderByIdDesc) {
            sortedArticles = Util.reverseList(sortedArticles);
        }
        // 정렬 로직 끝

        System.out.printf("== 게시물 리스트(%d개) ==\n", sortedArticles.size());
        System.out.println("번호 | 제목");
        sortedArticles.forEach(
                article -> System.out.printf("%d | %s\n", article.id, article.subject)
        );
    }

    public Article findById(int id) {
        return articles.stream()
                .filter(article -> article.id == id)
                .findFirst() // 찾은 것중에 처음 것을 리턴
                .orElse(null); // 찾지 못했다면 null을 리턴
    }


}
