import java.util.Arrays;

public class AppTest {
    public static void main(String[] args) {
        String queryString = "a=1&b=2&c=3";

        String[] queryStringBits = queryString.split("&");

        System.out.println(Arrays.toString(queryStringBits));
        int a=0;
        int b=0;
        int c=0;
        for(String bit : queryStringBits){
            String[] bits = bit.split("=");
            //System.out.println(Arrays.toString(bits));

            String paramKey = bits[0];
            String paramValue = bits[1];

            if(paramKey.equals("a")){
                a = Integer.parseInt(paramValue);
            }else if(paramKey.equals("b")){
                b = Integer.parseInt(paramValue);
            }else if(paramKey.equals("c")){
                c = Integer.parseInt(paramValue);
            }
        }
        System.out.printf("a: %d\n",a);
        System.out.printf("b: %d\n",b);
        System.out.printf("c: %d\n",c);

    }
}
