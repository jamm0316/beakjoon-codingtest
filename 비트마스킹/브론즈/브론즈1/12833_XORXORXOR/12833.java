package bitmask;

import java.util.*;
import java.io.*;

public class P12833 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int result = (C % 2 == 1) ? A ^ B : A;
        System.out.println(result);
    }
}
