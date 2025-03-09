import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest {
    public static void main(String[] args) {
        String queryString = "d=4&a=1&b=2&c=3&f=6";

        String[] queryStringBits = queryString.split("&");

        System.out.println(Arrays.toString(queryStringBits));
        List<String> paramsKey = new ArrayList<>();
        List<Integer> paramsValue = new ArrayList<>();

        for(String bit : queryStringBits){
            String[] bits = bit.split("=");
            //System.out.println(Arrays.toString(bits));
            paramsKey.add(bits[0]); //d a b c f
            paramsValue.add(Integer.parseInt(bits[1])); //4 1 2 3 6
        }
        for(int i=0; i<paramsKey.size(); i++){
            String paramKey = paramsKey.get(i);
            int paramValue = paramsValue.get(i);

            System.out.printf("%s : %d\n", paramKey, paramValue);
        }

    }
}
