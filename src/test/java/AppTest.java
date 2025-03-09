import java.util.*;

public class AppTest {
    public static void main(String[] args) {
        String queryString1 = "id=1&subject=제목1&content=내용1&writerName=김철수&boardId=1";
        String queryString2 = "id=2&subject=제목2&content=내용2&writerName=신유리&boardId=2";


        Map<String,String> params1 = Util.getParams(queryString1);
        Map<String,String> params2 = Util.getParams(queryString2);
        System.out.println(params1);
        System.out.println(params2);
    }
}

class Util{
    static Map<String, String> getParams(String queryStr){
        Map<String,String> params = new LinkedHashMap<>();
        String[] queryStrBits = queryStr.split("&");

        for(String Bit : queryStrBits){
            String[] bit = Bit.split("=");

            params.put(bit[0],bit[1]);
        }
        return params;
    }
}
