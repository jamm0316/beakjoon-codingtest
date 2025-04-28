import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    static Node root;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 빠른 입력/출력 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력을 저장할 리스트
        List<Integer> preorder = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null && !line.equals("")) {
            preorder.add(Integer.parseInt(line));
        }

        // 트리 만들기
        root = new Node(preorder.get(0));
        for (int i = 1; i < preorder.size(); i++) {
            insert(root, preorder.get(i));
        }

        // 후위순회하면서 출력
        postOrder(root);

        // 출력
        System.out.print(sb.toString());
    }

    // 트리에 삽입하는 메소드
    static void insert(Node node, int val) {
        if (val < node.val) {
            if (node.left == null) {
                node.left = new Node(val);
            } else {
                insert(node.left, val);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(val);
            } else {
                insert(node.right, val);
            }
        }
    }

    // 후위순회 메소드
    static void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.val).append("\n");
    }
}