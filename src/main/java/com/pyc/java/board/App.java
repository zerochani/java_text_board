package com.pyc.java.board;

import com.pyc.java.board.container.Container;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class App {
    List<Article> articles = new ArrayList<>();
    int lastId = 0;
    void makeArticleTestData(){
        IntStream.rangeClosed(1,100)
                .forEach(i->articles.add(new Article(i, "제목" + i, "내용" + i)));
    }
    public void run(){
        System.out.println("== 자바 텍스트 게시판 시작 ==");

        makeArticleTestData();

        while(true){
            System.out.print("명령) ");
            String cmd = Container.sc.nextLine();

            Rq rq = new Rq(cmd);

            if(rq.getUrlPath().equals("/user/article/write")){
                actionUserArticleWrite();
                lastId++;
            }else if(rq.getUrlPath().equals("/user/article/list")){
                actionUserArticleList(rq);
            }
            else if(rq.getUrlPath().equals("/user/article/detail")){
                actionUserArticleDetail(rq);
            }else if(rq.getUrlPath().equals("/user/article/modify")){
                actionUserArticleModify(rq);
            }else if(rq.getUrlPath().equals("/user/article/delete")){
                actionUserArticleDelete(rq);
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


    public void actionUserArticleDelete(Rq rq){
        if (articles.isEmpty()) {
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return;
        }

        Map<String, String> params = rq.getParams();

        if (!params.containsKey("id")) {
            System.out.println("id 값을 입력해주세요.");
            return;
        }

        int id = 0;

        try {
            id = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
            System.out.println("id를 정수형태로 입력해주세요.");
            return;
        }

        if (id > articles.size()) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        Article article = findById(id);
        if(article==null){
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
            return;
        }
        articles.remove(article);

        System.out.printf("%d번 게시물이 삭제되었습니다.\n",id);
    }

    public void actionUserArticleModify(Rq rq){
        if (articles.isEmpty()) {
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return;
        }

        Map<String, String> params = rq.getParams();

        if (!params.containsKey("id")) {
            System.out.println("id 값을 입력해주세요.");
            return;
        }

        int id = 0;

        try {
            id = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
            System.out.println("id를 정수형태로 입력해주세요.");
            return;
        }

        Article article = findById(id);
        if(article==null){
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
            return;
        }

        System.out.printf("== %d번 게시물 수정 ==\n", article.id);
        System.out.print("제목 : ");
        article.subject = Container.sc.nextLine();

        System.out.print("내용 : ");
        article.content = Container.sc.nextLine();

        System.out.printf("%d번 게시물이 수정되었습니다.\n", article.id);
    }

    public void actionUserArticleWrite(){
        lastId = articles.get(articles.size()-1).id;
        System.out.println("== 게시물 작성 ==");

        System.out.print("제목 : ");
        String subject = Container.sc.nextLine();

        System.out.print("내용 : ");
        String content = Container.sc.nextLine();

        int id = ++lastId;

        Article article = new Article(id,subject,content);
        //리스트에 담기
        articles.add(article);

        System.out.println("생성된 게시물 객체 : " + article);

        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);

    }

    public void actionUserArticleDetail(Rq rq){
        if(articles.isEmpty()){
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return;
        }

        Map<String,String> params = rq.getParams();

        if(!params.containsKey("id")){
            System.out.println("id 값을 입력해주세요.");
            return;
        }
        int id = 0;
        try{
            id = Integer.parseInt(params.get("id"));
        }catch(NumberFormatException e){
            System.out.println("id를 정수형태로 입력해주세요.");
            return;
        }

        Article article = findById(id);
        if(article==null){
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n",id);
            return;
        }

        System.out.println("==게시물 상세보기 ==");
        System.out.printf("번호: %d\n", article.id);
        System.out.printf("제목: %s\n", article.subject);
        System.out.printf("내용: %s\n", article.content);
    }

    public void actionUserArticleList(Rq rq){
        Map<String,String> params = rq.getParams();
        if(articles.isEmpty()){
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return;
        }

        //검색 기능 시작
        List<Article> filteredArticles = articles;
        if(params.containsKey("searchKeyWord")){
            String searchKeyWord = params.get("searchKeyWord");

            filteredArticles = new ArrayList<>();
            articles.stream()
                    .filter(article
                            -> article.subject.contains(searchKeyWord) || article.content.contains(searchKeyWord))
                    .forEach(filteredArticles::add);
        }
        //검색 기능 끝

        //정렬 로직 시작
        boolean orderByIdDesc = true;
        if(params.containsKey("orderBy") && params.get("orderBy").equals("IdAsc")){
            orderByIdDesc = false;
        }

        List<Article> sortedArticles = filteredArticles;
        if(orderByIdDesc){
            sortedArticles = Util.reverseList(sortedArticles);
        }
        //정렬 로직 끝
        System.out.printf("==게시물 리스트(%d개) ==\n", sortedArticles.size());
        System.out.println("번호 | 제목");
        sortedArticles.forEach(article-> System.out.printf("%d | %s\n", article.id, article.subject));
    }

    public Article findById(int id){
        return articles.stream()
                .filter(article->article.id == id)
                .findFirst()//찾은 것 중에 처음 것을 리턴
                .orElse(null); //찾지 못했다면 null을 리턴
    }

}
