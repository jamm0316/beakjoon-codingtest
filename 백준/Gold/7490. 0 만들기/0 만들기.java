import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<String> results;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            results = new ArrayList<>();
            
            backtrack(1, new char[N - 1]);
            
            for (String result : results) {
                sb.append(result).append('\n');
            }
            sb.append('\n'); // 테스트 케이스 구분
        }
        
        System.out.print(sb);
    }
    
    static void backtrack(int pos, char[] operators) {
        if (pos == N) {
            if (calculate(operators) == 0) {
                results.add(buildExpression(operators));
            }
            return;
        }
        
        operators[pos - 1] = ' ';
        backtrack(pos + 1, operators);
        
        operators[pos - 1] = '+';
        backtrack(pos + 1, operators);
        
        operators[pos - 1] = '-';
        backtrack(pos + 1, operators);
    }
    
    static int calculate(char[] operators) {
        List<Integer> numbers = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        
        int currentNum = 1;
        
        for (int i = 0; i < operators.length; i++) {
            if (operators[i] == ' ') {
                currentNum = currentNum * 10 + (i + 2);
            } else {
                numbers.add(currentNum);
                ops.add(operators[i]);
                currentNum = i + 2;
            }
        }
        numbers.add(currentNum); // 마지막 숫자 추가
        
        int result = numbers.get(0);
        for (int i = 0; i < ops.size(); i++) {
            if (ops.get(i) == '+') {
                result += numbers.get(i + 1);
            } else {
                result -= numbers.get(i + 1);
            }
        }
        
        return result;
    }
    
    static String buildExpression(char[] operators) {
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        
        for (int i = 0; i < operators.length; i++) {
            sb.append(operators[i]);
            sb.append(i + 2);
        }
        
        return sb.toString();
    }
}