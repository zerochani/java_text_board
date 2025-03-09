import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest {
    public static void main(String[] args) {
        String queryString = "id=1&subject=제목1&content=내용1&writerName=김철수&boardId=1";

        String[] queryStringBits = queryString.split("&");

        System.out.println(Arrays.toString(queryStringBits));
        List<String> paramsKey = new ArrayList<>();
        List<String> paramsValue = new ArrayList<>();

        for(String bit : queryStringBits){
            String[] bits = bit.split("=");
            //System.out.println(Arrays.toString(bits));
            paramsKey.add(bits[0]);
            paramsValue.add(bits[1]);
        }
//        for(int i=0; i<paramsKey.size(); i++){
//            String paramKey = paramsKey.get(i);
//            String paramValue = paramsValue.get(i);
//
//            System.out.println("%s : %s\n", paramKey, paramValue);
//        }
        System.out.println(paramsKey);
        System.out.println(paramsValue);

        //subject 인덱스 구하기 및 키 subject의 값 구하기
        int findIdx = paramsKey.indexOf("subject");

        System.out.println(paramsValue.get(findIdx));

    }
}
