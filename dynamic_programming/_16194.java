package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제: 카드 구매하기2
 * 링크: https://www.acmicpc.net/problem/16194
 * 일자: 2025-11-10
 * 유형: DP
 *
 * 프로그램 로직
 * 1. i장을 만들기 위해 모든 j(1~i) 장짜리 팩을 고려한다.
 * 2. dp[i] = min(dp[i-j] + arr[j]) 로 최소 비용을 구한다.
 * 3. dp를 1부터 N까지 채우고 dp[N]을 출력한다.
 */

public class _16194 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] arr = new int [N+1];
        int [] dp = new int [N+1];
        final int INF = 1_000_000_000;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 1; i <= N; i++) { // i장을 만들기
            for (int j = 1; j <= i; j++) { // 마지막에 j장짜리 팩을 산다
                dp[i] = Math.min(dp[i], dp[i - j] + arr[j]);
            }
        }

        print(dp[N]);
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
