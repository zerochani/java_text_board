package com.pyc.java.board;

import java.util.*;

public class Main {
    static void makeArticleTestData(List<Article> articles){
        articles.add(new Article(1,"제목1","내용1"));
        articles.add(new Article(2,"제목2","내용2"));
        articles.add(new Article(3,"제목3","내용3"));
    }
    public static void main(String[] args) {
        System.out.println("== 자바 텍스트 게시판 시작 ==");
        Scanner sc = new Scanner(System.in);
        List<Article> articles = new ArrayList<>();
        makeArticleTestData(articles);
        int lastId = articles.get(articles.size()-1).id;


        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            if(rq.getUrlPath().equals("/user/article/write")){
                System.out.println("== 게시물 작성 ==");

                System.out.print("제목 : ");
                String subject = sc.nextLine();

                System.out.print("내용 : ");
                String content = sc.nextLine();

                int id = ++lastId;

                Article article = new Article(id,subject,content);
                //리스트에 담기
                articles.add(article);

                System.out.println("생성된 게시물 객체 : " + article);

                System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
            }else if(rq.getUrlPath().equals("/user/article/list")){
                if(articles.isEmpty()){
                    System.out.println("현재 게시물이 존재하지 않습니다.");
                    continue;
                }

                System.out.println("번호 | 제목");

                //역순 출력
                for(int i = articles.size()-1; i>=0; i--){
                    Article article = articles.get(i);
                    System.out.printf("%d | %s\n", article.id, article.subject);
                }
            }
            else if(rq.getUrlPath().equals("/user/article/detail")){
                if(articles.isEmpty()){
                    System.out.println("현재 게시물이 존재하지 않습니다.");
                    continue;
                }

                Map<String,String> params = rq.getParams();

                if(!params.containsKey("id")){
                    System.out.println("id 값을 입력해주세요.");
                    continue;
                }
                int id = 0;
                try{
                    id = Integer.parseInt(params.get("id"));
                }catch(NumberFormatException e){
                    System.out.println("id를 정수형태로 입력해주세요.");

                }

                if(id>articles.size()){
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
                    continue;
                }
                Article article = articles.get(id-1);

                System.out.println("==게시물 상세보기 ==");
                System.out.printf("번호: %d\n", article.id);
                System.out.printf("제목: %s\n", article.subject);
                System.out.printf("내용: %s\n", article.content);
            }else if(rq.getUrlPath().equals("exit")){
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

class Rq{
    String url;

    Map<String, String> params;
    String urlPath;

    Rq(String url){
        this.url = url;
        params = Util.getParamsFromUrl(this.url);
        urlPath = Util.getPathFromUrl(this.url);
    }

    Map<String,String> getParams(){
        return params;
    }

    String getUrlPath(){
        return urlPath;
    }
}

class Util{
    static Map<String, String> getParamsFromUrl(String url){
        Map<String,String> params = new LinkedHashMap<>();
        String[] urlBits = url.split("\\?",2);

        if(urlBits.length==1) return params;

        String queryStr = urlBits[1];

        for(String bit : queryStr.split("&")){
            String[] bitBits = bit.split("=",2);

            if(bitBits.length==1){
                continue;
            }
            params.put(bitBits[0],bitBits[1]);
        }

        return params;
    }

    static String getPathFromUrl(String url){
        return url.split("\\?",2)[0];
    }
}

