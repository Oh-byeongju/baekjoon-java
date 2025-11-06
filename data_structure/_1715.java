package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class _1715 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int result = 0;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            pq.add(temp);
        }

        // 1. 큐 값이 하나만 남을때 까지 반복하며
        // 2. 큐에서 가장 작은값 두개를 뽑아 더한 뒤, 결과값에 할당
        // 3. 할당된 값은 다시 우선순위 큐로 넣음
        while (pq.size() > 1) {
            int A = pq.poll();
            int B = pq.poll();
            int nowNum = A + B;

            result += nowNum;
            pq.add(A + B);
        }

        print(result);
    }

    public static void print(Object msg) {
        System.out.println(msg);
    }
}