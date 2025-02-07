import java.io.*;
import java.util.*;

//그리디
//dp?
//g수 / 1g당 가격
public class Main {
     static class Jewerly{
         int weight;
         int value;

         public Jewerly(int weight, int value) {
             this.weight = weight;
             this.value = value;
         }
         
         public int getWeight() {
             return weight;
         }

         public int getValue() {
             return value;
         }

         public String toString() {
             return weight + " " + value;
         }
     }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int bagWeight = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        List<Jewerly> jewerly = new ArrayList<>();
        int result = 0;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewerly.add(new Jewerly(weight, value));
        }
    
        jewerly.sort(Comparator.comparingInt(Jewerly::getValue).reversed());
        
        for (int i = 0; i < N; i++) {
            int curWeight = jewerly.get(i).weight;
            int curValue = jewerly.get(i).value;

            if (bagWeight >= curWeight) {
                bagWeight -= curWeight;
                result += curWeight * curValue;
            } else {
                result += curValue * bagWeight;
                break;
            }
        }
        
        System.out.println(result);
    }
    
}
