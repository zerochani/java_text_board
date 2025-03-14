package com.pyc.java.board;

import com.pyc.java.board.container.Container;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        App app = new App();
        app.run();
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

    //이 함수는 원본리스트를 훼손하지 않고, 복사본을 만든다.
    //즉 정렬이 반대인 복사본 리스트를 만들어서 반환.
    static <T> List<T> reverseList(List<T> list){
        List<T> reverse = new ArrayList<>(list.size());
        //역순 출력
        for(int i = list.size()-1; i>=0; i--){
            reverse.add(list.get(i));
        }
        return reverse;
    }

}


