import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (isRoundStrRange(str, 0, str.length() - 1)) {
                sb.append(0);
            } else if (isSemiRoundStr(str)) {
                sb.append(1);
            } else {
                sb.append(2);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    private static boolean isRoundStrRange(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isSemiRoundStr(String str) {
        int length = str.length();
        int left = 0;
        int right = length - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return isRoundStrRange(str, left + 1, right) || isRoundStrRange(str, left, right - 1);
            }
            left++;
            right--;
        }
        return false;
    }
}
