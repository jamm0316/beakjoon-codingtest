import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int people = Integer.parseInt(st.nextToken());
        int partyCount = Integer.parseInt(st.nextToken());

        parent = new int[people + 1];
        for (int i = 1; i <= people; i++) parent[i] = i;

        //진실을 아는 사람
        st = new StringTokenizer(br.readLine());
        int truthTellerCount = Integer.parseInt(st.nextToken());

        //진실을 아는 사람이 있으면, 리스트 생성
        Set<Integer> truthSet = new HashSet<>();
        for (int i = 0; i < truthTellerCount; i++) {
            truthSet.add(Integer.parseInt(st.nextToken()));
        }
        List<List<Integer>> parties = new ArrayList<>();

        //파티 정보 저정 + union
        for (int i = 0; i < partyCount; i++) {
            st = new StringTokenizer(br.readLine());
            int attendeeCount = Integer.parseInt(st.nextToken());

            List<Integer> attendees = new ArrayList<>();
            for (int j = 0; j < attendeeCount; j++) {
                attendees.add(Integer.parseInt(st.nextToken()));
            }

            //같은 파티의 사람들은 모두 같은 집합으로 union
            for (int j = 1; j < attendees.size(); j++) {
                union(attendees.get(0), attendees.get(j));
            }
            parties.add(attendees);
        }

        //과장해서 말할 수 있는 파티 수 세기
        int result = 0;
        for (List<Integer> party : parties) {
            boolean canLie = true;
            for (int person : party) {
                for (int truth : truthSet) {
                    if (find(person) == find(truth)) {
                        canLie = false;
                        break;
                    }
                }
                if (!canLie) break;
            }
            if (canLie) result++;
        }
        System.out.println(result);
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}
