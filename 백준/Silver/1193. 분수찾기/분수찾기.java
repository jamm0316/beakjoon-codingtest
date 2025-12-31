import java.io.*;

public class Main {    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());

        int diagonal = 1;
        while (diagonal * (diagonal + 1) / 2 < X) {
            diagonal++;
        }

        int position = X - (diagonal - 1) * diagonal / 2;

        int numerator, denominator;

        if (diagonal % 2 == 1) {
            numerator = diagonal - position + 1;
            denominator = position;
        } else {
            numerator = position;
            denominator = diagonal - position + 1;
        }

        System.out.println(numerator + "/" + denominator);
    }
}