import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //input String 값, 폭발 문자열 받기
        String input = br.readLine(), explosion = br.readLine();;

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            if (sb.length() >= explosion.length()) {
                boolean match = true;
                for (int j = 0; j < explosion.length(); j++) {
                    if (sb.charAt(sb.length() - explosion.length() + j) != explosion.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    sb.delete(sb.length() - explosion.length(), sb.length());
                }
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}
