import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = str.length();
        
        int totalA = 0;
        for (char c : str.toCharArray()) {
            if (c == 'a') totalA++;
        }

        String doubleStr = str + str;
        
        int currentB = 0;
        for (int i = 0; i < totalA; i++) {
            if (doubleStr.charAt(i) == 'b') currentB++;
        }

        int minSwap = currentB;

        for (int i = 1; i < N; i++) {
            if (doubleStr.charAt(i - 1) == 'b') currentB--;
            if (doubleStr.charAt(i + totalA - 1) == 'b') currentB++;
            minSwap = Math.min(minSwap, currentB);
        }
        System.out.println(minSwap);
    }
}
