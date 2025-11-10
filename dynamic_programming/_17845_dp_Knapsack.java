package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제: 수강 과목
 * 링크: https://www.acmicpc.net/problem/17845
 * 일자: 2025-11-10
 * 유형: 0-1 배낭
 *
 * 프로그램 로직
 * 1. dp[i][w] = i번 과목까지 고려했을 때 시간 w에서 얻을 수 있는 최대 중요도.
 * 2. 과목 i를 담을 수 있으면 max(안 담음, 담음) 비교하여 dp 갱신.
 * 3. 모든 과목과 시간을 순회한 뒤 dp[N][W]를 출력.
 */

public class _17845_dp_Knapsack {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 문제가 배낭 형태여서, 배낭형식의 주석 사용
        int W = Integer.parseInt(st.nextToken()); // 최대 무게
        int N = Integer.parseInt(st.nextToken()); // 물건의 개수

        int [] value = new int [N + 1];  // 각 물건의 가치
        int [] weight = new int [N + 1]; // 각 물건의 무게

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            value[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        int [][] dp = new int[N+1][W+1];

        // 계산 시작
        for (int i = 1; i <= N; i++) { // 물건 개수만큼 반복
            for (int w = 0; w <= W; w++) { // 최대 무게만큼 반복
                // 못 담는 경우
                dp[i][w] = dp[i-1][w];

                if (w >= weight[i]) {
                    dp[i][w] = Math.max(
                            dp[i][w],
                            dp[i-1][w - weight[i]] + value[i]
                    );
                }
            }
        }
        print(dp[N][W]);
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
