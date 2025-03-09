import java.util.Arrays;

public class AppTest {
    public static void main(String[] args) {
        String queryString = "a=1&b=2&c=3";

        String[] queryStringBits = queryString.split("&");

        System.out.println(Arrays.toString(queryStringBits));

        for(String bit : queryStringBits){
            String[] bits = bit.split("=");
            //System.out.println(Arrays.toString(bits));

            String paramKey = bits[0];
            String paramValue = bits[1];

            System.out.printf("%s: %s\n", paramKey, paramValue);
        }

    }
}
