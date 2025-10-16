import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int T, N, E, startVirus;
    static final int INF = Integer.MAX_VALUE;
    static class Computer {
        int end, cost;

        Computer(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            startVirus = Integer.parseInt(st.nextToken());

            List<List<Computer>> computers = new ArrayList<>();
            for (int i = 0; i < N + 1; i++) {
                computers.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int end = Integer.parseInt(st.nextToken());
                int start = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                computers.get(start).add(new Computer(end, cost));
            }

            int[] result = dijkstra(startVirus, computers);
            sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }
        System.out.println(sb);
    }

    private static int[] dijkstra(int start, List<List<Computer>> computers) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Computer> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Computer(start, 0));

        int infected = 1;
        int totalCost = 0;
        while (!pq.isEmpty()) {
            Computer cur = pq.poll();
            if (cur.cost > dist[cur.end]) continue;
            for (Computer next : computers.get(cur.end)) {
                if (dist[next.end] > dist[cur.end] + next.cost) {
                    dist[next.end] = dist[cur.end] + next.cost;
                    pq.offer(new Computer(next.end, dist[next.end]));
                }
            }
        }
        infected = (int) Arrays.stream(dist)
                .filter(i -> i != INF)
                .count();
        IntStream.range(0, dist.length)
                .forEach(i -> {
                    if (dist[i] == INF) dist[i] = 0;
                });
        totalCost = Arrays.stream(dist).max().getAsInt();
        return new int[]{infected, totalCost};
    }
}
