package com.pyc.java.board.boundedContext.article.controller;

import com.pyc.java.board.base.Rq;
import com.pyc.java.board.boundedContext.article.dto.Article;
import com.pyc.java.board.boundedContext.article.service.ArticleService;
import com.pyc.java.board.boundedContext.member.dto.Member;
import com.pyc.java.board.container.Container;
import com.pyc.java.board.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ArticleController {

    private ArticleService articleService;

    public ArticleController(){
        articleService = Container.articleService;
    }

    public void doDelete(Rq rq) {
        int id = rq.getIntParam("id",0);

        if(id==0){
            System.out.println("올바른 값을 입력해주세요.");
            return;
        }

        Article article = articleService.findById(id);
        Member member = (Member) rq.getSessionAttr("loginedMember");

        if(article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        if(!(article.getMemberId() == member.getId())){
            System.out.println("게시물에 접근권한이 없습니다.");
            return;
        }

        articleService.delete(id);

        System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
    }

    public void doModify(Rq rq) {

        int id = rq.getIntParam("id",0);

        if(id==0){
            System.out.println("올바른 값을 입력해주세요.");
            return;
        }

        Article article = articleService.findById(id);
        Member member = (Member) rq.getSessionAttr("loginedMember");

        if(article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        if(!(article.getMemberId() == member.getId())){
            System.out.println("게시물에 접근 권한이 없습니다.");
            return;
        }

        System.out.printf("== %d번 게시물 수정 ==\n", article.getId());
        System.out.print("제목 : ");
        String subject = Container.sc.nextLine();

        System.out.print("내용 : ");
        String content = Container.sc.nextLine();

        articleService.modify(id,subject,content);

        System.out.printf("%d번 게시물이 수정되었습니다.\n", article.getId());
    }

    public void doWrite(Rq rq) {
//        lastId = articles.get(articles.size() - 1).id;
        System.out.println("== 게시물 작성 ==");

        System.out.print("제목 : ");
        String subject = Container.sc.nextLine();

        System.out.print("내용 : ");
        String content = Container.sc.nextLine();

        Member member = (Member) rq.getSessionAttr("loginedMember");

        int id = articleService.write(subject,content, member.getUsername(),member.getId());


        System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
    }

    public void showDetail(Rq rq) {

        int id = rq.getIntParam("id",0);

        if(id==0){
            System.out.println("올바른 값을 입력해주세요.");
            return;
        }

        Article article = articleService.findById(id);

        if(article == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.println("== 게시물 상세보기 ==");
        System.out.printf("번호 : %d\n", article.getId());
        System.out.printf("제목 : %s\n", article.getSubject());
        System.out.printf("내용 : %s\n", article.getContent());
        System.out.printf("작성자 : %s\n", article.getWriterName());
    }

    public void showList(Rq rq) {


        String searchKeyWord = rq.getParam("searchKeyWord","");
        String orderBy = rq.getParam("orderBy", "idDesc");


        List<Article> articles = articleService.findAll(searchKeyWord, orderBy);

        if(articles.isEmpty()){
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return;
        }

        System.out.printf("== 게시물 리스트(%d개) ==\n", articles.size());
        System.out.println("번호 | 제목 | 작성자");
        articles.forEach(
                article -> System.out.printf("%d | %s | %s\n", article.getId(), article.getSubject(),article.getWriterName())
        );
    }



}
