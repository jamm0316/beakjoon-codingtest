/*
1. 사람을 오름차순으로 정렬한다.
2. i <= j가 될 때까지 반복
3. j(무거운 사람은 무조건 출발) (j--)
4. j + i 가 limit보다 작거나 같으면 i도 같이 출발 (i++)
5. count++
*/
import java.util.*;

class Solution {
    static int count;
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        
        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++;
            }
            j--;
            count++;
        }
        return count;
    }
}