package com.pyc.java.board;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("== 자바 텍스트 게시판 시작 ==");
        Scanner sc = new Scanner(System.in);
        int lastId = 0; //게시물 번호
        Article lastArticle = null;

        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if(cmd.equals("/user/article/write")){
                System.out.println("== 게시물 작성 ==");

                System.out.print("제목 : ");
                String subject = sc.nextLine();

                System.out.print("내용 : ");
                String content = sc.nextLine();

                int id = ++lastId;

                Article article = new Article(id,subject,content);
                lastArticle = article;

                System.out.println("생성된 게시물 객체 : " + article);

                System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
            }else if(cmd.equals("/user/article/detail")){
                Article article = lastArticle;

                if(article == null){
                    System.out.println("게시물이 존재하지 않습니다.");
                    continue;
                }

                System.out.println("==게시물 상세보기 ==");
                System.out.printf("번호: %d\n", article.id);
                System.out.printf("제목: %s\n", article.subject);
                System.out.printf("내용: %s\n", article.content);
            }else if(cmd.equals("exit")){
                System.out.println("프로그램을 종료합니다.");
                break;
            }else{
                System.out.println("잘못된 명령어입니다.");
            }
            System.out.printf("입력받은 명령어 : %s\n", cmd);

        }
        System.out.println("== 자바 텍스트 게시판 끝 ==");
        sc.close();

    }
}

class Article{
    int id;
    String subject;
    String content;

    Article(int id, String subject, String content){
        this.id = id;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String toString(){
        return "{id: %d, subject: \"%s\", content: \"%s\"}".formatted(id,subject,content);
    }
}