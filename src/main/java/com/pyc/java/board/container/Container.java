package com.pyc.java.board.container;

import com.pyc.java.board.boundedContext.article.controller.ArticleController;
import com.pyc.java.board.boundedContext.article.repository.ArticleRepository;
import com.pyc.java.board.boundedContext.article.service.ArticleService;

import java.util.Scanner;

public class Container {
    public static Scanner sc;

    public static ArticleRepository articleRepository;

    public static ArticleService articleService;

    public static ArticleController articleController;
    //static메서드는 프로그램 실행되자마자 실행
    static{
        sc = new Scanner(System.in);

        articleRepository = new ArticleRepository();
        articleService = new ArticleService();
        articleController = new ArticleController();
    }
}
