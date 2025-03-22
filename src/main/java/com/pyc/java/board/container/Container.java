package com.pyc.java.board.container;

import com.pyc.java.board.boundedContext.article.controller.ArticleController;
import com.pyc.java.board.boundedContext.article.repository.ArticleRepository;
import com.pyc.java.board.boundedContext.article.service.ArticleService;
import com.pyc.java.board.boundedContext.member.controller.MemberController;
import com.pyc.java.board.boundedContext.member.repository.MemberRepository;
import com.pyc.java.board.boundedContext.member.service.MemberService;
import com.pyc.java.board.interceptor.NeedLoginInterceptor;
import com.pyc.java.board.interceptor.NeedLogoutInterceptor;
import com.pyc.java.board.session.Session;

import java.util.Scanner;

public class Container {
    public static Scanner sc;
    public static Session session;

    public static NeedLogoutInterceptor needLogoutInterceptor;
    public static NeedLoginInterceptor needLoginInterceptor;

    public static MemberRepository memberRepository;
    public static ArticleRepository articleRepository;

    public static MemberService memberService;
    public static ArticleService articleService;

    public static MemberController memberController;
    public static ArticleController articleController;
    //static메서드는 프로그램 실행되자마자 실행
    static{
        sc = new Scanner(System.in);
        session = new Session();

        needLogoutInterceptor = new NeedLogoutInterceptor();
        needLoginInterceptor = new NeedLoginInterceptor();

        memberRepository = new MemberRepository();
        memberService = new MemberService();
        memberController = new MemberController();

        articleRepository = new ArticleRepository();
        articleService = new ArticleService();
        articleController = new ArticleController();
    }
}
