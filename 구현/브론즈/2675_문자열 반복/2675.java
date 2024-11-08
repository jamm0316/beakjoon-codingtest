import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //initialize array
        int queries = Integer.parseInt(reader.readLine());
        for (int i = 0; i < queries; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            String repeatCount = st.nextToken();
            String text = st.nextToken();
            
            for (int j = 0; j < text.length(); j++) {
                for(int k = 0; k < Integer.parseInt(repeatCount); k++) {
                    sb.append(text.charAt(j));
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
