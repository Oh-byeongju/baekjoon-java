package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _11000 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 끝나는 시간 저장하는 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        // 값 입력
        int [][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < 2; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째, 두 번쨰 값 순서로 정렬
        Arrays.sort(arr, (a,b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        // 끝나는 시간을 기준으로 비교 시작
        for (int[] row : arr) {
            int a = row[0];
            int b = row[1];

            // 재사용 로직
            if (!pq.isEmpty() && pq.peek() <= a) {
                pq.poll();
            }

            pq.offer(b);
        }

        print(pq.size());
    }

    public static void print(Object msg) {
        System.out.println(msg);
    }
}