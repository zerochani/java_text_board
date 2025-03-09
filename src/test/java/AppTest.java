import java.util.*;

public class AppTest {
    public static void main(String[] args) {
        String queryString = "id=1&subject=제목1&content=내용1&writerName=김철수&boardId=1";

        String[] queryStringBits = queryString.split("&");

        Map<String,String> params = new LinkedHashMap<>();

        for(String bit : queryStringBits){
            String[] bits = bit.split("=");
            params.put(bits[0], bits[1]);
        }

        System.out.println(params);

        System.out.println("==반복문을 이용한 순회==");
        params.forEach((key,value)->
            System.out.printf("%s : %s\n", key,value));

    }
}
