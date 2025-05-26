import java.util.*;
import java.util.stream.*;

class Solution {
    static StringBuilder sb = new StringBuilder();
    public String solution(String s) {
        int[] arr = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        sb.append(min).append(" ").append(max);
        return sb.toString();
    }
}