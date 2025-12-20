import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 등급 -> 과목평점 매핑
        Map<String, Double> gradePoint = new HashMap<>();
        gradePoint.put("A+", 4.5);
        gradePoint.put("A0", 4.0);
        gradePoint.put("B+", 3.5);
        gradePoint.put("B0", 3.0);
        gradePoint.put("C+", 2.5);
        gradePoint.put("C0", 2.0);
        gradePoint.put("D+", 1.5);
        gradePoint.put("D0", 1.0);
        gradePoint.put("F", 0.0);

        double sumCredit = 0.0;      // 분모: 총 학점(P 제외)
        double sumWeighted = 0.0;    // 분자: 학점 * 과목평점 합(P 제외)

        for (int i = 0; i < 20; i++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);

            String subject = st.nextToken();      // 과목명 (사용 안 함)
            double credit = Double.parseDouble(st.nextToken());
            String grade = st.nextToken();

            // P는 계산에서 제외
            if (grade.equals("P")) continue;

            sumCredit += credit;
            sumWeighted += credit * gradePoint.get(grade);
        }

        double gpa = sumWeighted / sumCredit;

        System.out.println(gpa);
    }
}