import java.io.*;
import java.util.*;

public class Main {
    static int[] left = new int[26];
    static int[] right = new int[26];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = st.nextToken().charAt(0) - 'A';
            char leftChild = st.nextToken().charAt(0);
            char rightChild = st.nextToken().charAt(0);
            left[parent] = (leftChild == '.') ? -1 : leftChild - 'A';
            right[parent] = (rightChild == '.') ? -1 : rightChild - 'A';
        }

        preorder(0);
        sb.append('\n');
        inorder(0);
        sb.append('\n');
        postorder(0);
        System.out.println(sb);
    }

    static void preorder(int node) {
        if (node == -1) return;
        sb.append((char) (node + 'A'));
        preorder(left[node]);
        preorder(right[node]);
    }

    static void inorder(int node) {
        if (node == -1) return;
        inorder(left[node]);
        sb.append((char) (node + 'A'));
        inorder(right[node]);
    }

    static void postorder(int node) {
        if (node == -1) return;
        postorder(left[node]);
        postorder(right[node]);
        sb.append((char) (node + 'A'));
    }
}
