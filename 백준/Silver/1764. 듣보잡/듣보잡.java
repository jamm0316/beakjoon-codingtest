import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, String> haveNotSeen = new HashMap<>();
        Map<String, String> haveNotListen = new HashMap<>();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            haveNotSeen.put(name, name);
        }

        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            haveNotListen.put(name, name);
        }

        if (haveNotSeen.size() < haveNotListen.size()) {
            for (String name : haveNotSeen.keySet()) {
                if (!haveNotListen.getOrDefault(name, "-1").equals("-1")) {
                    list.add(name);
                }
            }
        } else {
            for (String name : haveNotListen.keySet()) {
                if (!haveNotSeen.getOrDefault(name, "-1").equals("-1")) {
                    list.add(name);
                }
            }
        }
        list.sort(Comparator.naturalOrder());

        bw.write(String.valueOf(list.size()));
        bw.newLine();
        list.stream().forEach(i -> {
            try {
                bw.write(i);
                bw.newLine();
            } catch (IOException e) {
            }
        });

        br.close();
        bw.close();
    }
}
