import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] input = new int[8];
        for (int i = 0; i < 8; i++) {
            input[i] = sc.nextInt();
        }

        if (isAscending(input)) {
            System.out.println("ascending");
        } else if (isDescending(input)) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }

    private static boolean isAscending(int[] arr) {
        for (int i = 0; i < 8; i++) {
            if (arr[i] != i + 1) return false;
        }
        return true;
    }

    private static boolean isDescending(int[] arr) {
        for (int i = 0; i < 8; i++) {
            if (arr[i] != 8 - i) return false;
        }
        return true;
    }
}