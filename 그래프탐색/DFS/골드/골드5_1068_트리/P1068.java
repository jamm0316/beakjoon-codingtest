import java.io.*;
import java.util.*;

public class P1068 {
    static List<List<Integer>> tree = new ArrayList<>();
    static int leafNode, root, removeNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                tree.get(parent).add(i);
            }
        }

        st = new StringTokenizer(br.readLine());
        removeNode = Integer.parseInt(st.nextToken());

        if (removeNode == root) {
            System.out.println(0);
        } else {
            dfs(root);
            System.out.println(leafNode);
        }
    }

    private static void dfs(int current) {
        if (current == removeNode) {
            return;
        }

        boolean isLeaf = true;
        for (int each : tree.get(current)) {
            if (each != removeNode) {
                dfs(each);
                isLeaf = false;
            }
        }

        if (isLeaf) leafNode++;
    }
}
