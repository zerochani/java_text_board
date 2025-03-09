import java.util.*;

public class AppTest {
    public static void main(String[] args) {
        String queryString1 = "id=1&subject=제목1&content=내용1&writerName=김철수&boardId=1";

        String[] queryString1Bits = queryString1.split("&");
        String queryString2 = "id=2&subject=제목2&content=내용2&writerName=신유리&boardId=2";
        String[] queryString2Bits = queryString2.split("&");

        Map<String,String> params1 = new LinkedHashMap<>();

        for(String bit : queryString1Bits){
            String[] bits = bit.split("=");
            params1.put(bits[0], bits[1]);
        }

        Map<String,String> params2 = new LinkedHashMap<>();

        for(String bit : queryString2Bits){
            String[] bits = bit.split("=");
            params2.put(bits[0], bits[1]);
        }


        System.out.println(params1);
        System.out.println(params2);

        System.out.println("==반복문을 이용한 순회==");
        params1.forEach((key,value)->
            System.out.printf("%s : %s\n", key,value));

        System.out.println("==반복문을 이용한 순회==");
        params2.forEach((key,value)->
                System.out.printf("%s : %s\n", key,value));

    }
}
