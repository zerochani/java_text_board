import java.util.*;

public class AppTest {
    public static void main(String[] args) {
        String queryString = "/usr/article/write?id=1&subject=제목1&content=10+5=13&writerName=김철수&boardId=1";

        Map<String,String> params = Util.getParamsFromUrl(queryString);
        System.out.println(params);

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
}
