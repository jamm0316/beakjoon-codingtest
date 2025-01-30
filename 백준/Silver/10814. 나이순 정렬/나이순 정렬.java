import java.io.*;
import java.util.*;

public class Main {
    static class Person {
        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return age + " " + name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Person> student = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            student.add(new Person(age, name));
        }

        student.stream()
                .sorted(Comparator.comparingInt(p -> p.age))
                .forEach(p -> {
                    try {
                        bw.write(p.toString());
                        bw.newLine();
                    } catch (IOException e) {

                    }
                });
        bw.close();
        br.close();
    }
}
