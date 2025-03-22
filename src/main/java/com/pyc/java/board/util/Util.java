package com.pyc.java.board.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public static Map<String, String> getParamsFromUrl(String url){
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

    public static String getPathFromUrl(String url){
        return url.split("\\?",2)[0];
    }

    //이 함수는 원본리스트를 훼손하지 않고, 복사본을 만든다.
    //즉 정렬이 반대인 복사본 리스트를 만들어서 반환.
    public static <T> List<T> reverseList(List<T> list){
        List<T> reverse = new ArrayList<>(list.size());
        //역순 출력
        for(int i = list.size()-1; i>=0; i--){
            reverse.add(list.get(i));
        }
        return reverse;
    }

    public static String getNowDateStr(){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format1.format(System.currentTimeMillis());
        return dateStr;
    }

}
