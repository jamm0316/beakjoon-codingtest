import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String doc = br.readLine();
        String word = br.readLine();

        int count = 0;
        int i = 0;
        int n = doc.length();
        int m = word.length();

        while (i <= n - m) {
            boolean match = true;

            for (int j = 0; j < m; j++) {
                if (doc.charAt(i + j) != word.charAt(j)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                count++;
                i += m;     
            } else {
                i++;        
            }
        }

        System.out.println(count);
    }
}