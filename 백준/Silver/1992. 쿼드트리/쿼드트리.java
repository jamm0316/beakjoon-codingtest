import java.util.Scanner;

public class Main {
    private static String compressQuadTree(char[][] matrix, int x, int y, int size) {
        char firstValue = matrix[x][y];
        boolean same = true;

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (matrix[i][j] != firstValue) {
                    same = false;
                    break;
                }
            }
            if (!same) break;
        }

        if (same) {
            return String.valueOf(firstValue);
        }

        int halfSize = size / 2;
        String topLeft = compressQuadTree(matrix, x, y, halfSize);
        String topRight = compressQuadTree(matrix, x, y + halfSize, halfSize);
        String bottomLeft = compressQuadTree(matrix, x + halfSize, y, halfSize);
        String bottomRight = compressQuadTree(matrix, x + halfSize, y + halfSize, halfSize);

        return "(" + topLeft + topRight + bottomLeft + bottomRight + ")";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            matrix[i] = line.toCharArray();
        }

        String result = compressQuadTree(matrix, 0, 0, N);

        System.out.println(result);

        scanner.close();
    }
}