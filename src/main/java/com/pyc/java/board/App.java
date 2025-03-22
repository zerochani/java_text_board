package com.pyc.java.board;

import com.pyc.java.board.base.Rq;
import com.pyc.java.board.boundedContext.article.controller.ArticleController;
import com.pyc.java.board.boundedContext.article.dto.Article;
import com.pyc.java.board.boundedContext.member.dto.Member;
import com.pyc.java.board.container.Container;
import com.pyc.java.board.interceptor.Interceptor;
import com.pyc.java.board.util.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.pyc.java.board.container.Container.memberController;

public class App {
    public ArticleController articleController;

    public App(){
        articleController = Container.articleController;
    }

    public void run(){
        System.out.println("== 자바 텍스트 게시판 시작 ==");


        while(true){
            Rq rq = new Rq();

            Member member = (Member)rq.getSessionAttr("loginedMember");
            String promptName = "명령";
            if(member!=null){
                promptName = member.getUsername();
            }
            System.out.printf("%s) ", promptName);
            String cmd = Container.sc.nextLine();
            rq.setCommand(cmd);

            if(!runInterceptor(rq)){
                continue;
            }

            if(rq.getUrlPath().equals("/user/article/write")){
                articleController.doWrite(rq);
            }else if(rq.getUrlPath().equals("/user/article/list")){
                articleController.showList(rq);
            }
            else if(rq.getUrlPath().equals("/user/article/detail")){
                articleController.showDetail(rq);
            }else if(rq.getUrlPath().equals("/user/article/modify")){
                articleController.doModify(rq);
            }else if(rq.getUrlPath().equals("/user/article/delete")){
                articleController.doDelete(rq);
            }else if(rq.getUrlPath().equals("/user/member/join")){
                memberController.doJoin(rq);
            }else if(rq.getUrlPath().equals("/user/member/login")){
                memberController.doLogin(rq);
            }else if(rq.getUrlPath().equals("/user/member/logout")){
                memberController.doLogout(rq);
            }else if(rq.getUrlPath().equals("/user/member/mypage")){
                memberController.showMyPage(rq);
            }
            else if(rq.getUrlPath().equals("exit")){
                System.out.println("프로그램을 종료합니다.");
                break;
            }else{
                System.out.println("잘못된 명령어입니다.");
            }
            System.out.printf("입력받은 명령어 : %s\n", cmd);

        }
        System.out.println("== 자바 텍스트 게시판 끝 ==");
        Container.sc.close();
    }

    private boolean runInterceptor(Rq rq) {
        List<Interceptor> interceptors = new ArrayList<>();

        interceptors.add(Container.needLogoutInterceptor);
        interceptors.add(Container.needLoginInterceptor);

        for(Interceptor interceptor : interceptors){
            if(!interceptor.run(rq)){
                return false;
            }
        }
        return true;
    }

}
