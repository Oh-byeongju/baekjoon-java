package graph_traversal;

import java.io.*;
import java.util.*;

/**
 * 문제: 플로이드 (11404)
 * 링크: https://www.acmicpc.net/problem/11404
 * 일자: 2025-11-14
 * 유형: 플로이드-워셜 (모든 쌍 최단 경로)
 *
 * 프로그램 로직
 * 1. dist 초기값 설정 (자기 자신 초기화 필수)
 * 2. k(거쳐가는 도시), i(출발 도시), j(도착 도시) 순으로 for문 구현
 * 3. 값을 비교할 때는 i → k → j 가 비싼순으로 조건문 설정
 * 4. 결론: dist[i][j] : i -> j 최소 비용 (행렬) 저장
 */

public class _11404_floyd {
    static int N, M;
    static final int INF = (int)1e9;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N+1][N+1];

        // dist 배열 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // 자기 자신은 0
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()); // 출발
            int to = Integer.parseInt(st.nextToken());   // 도착
            int cost = Integer.parseInt(st.nextToken()); // 비용

            if (cost < dist[from][to]) {
                dist[from][to] = cost;
            }
        }

        // 플로이드 워셜 진행
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // cost 입력
                if (dist[i][j] == INF) {
                    sb.append(0);
                } else {
                    sb.append(dist[i][j]);
                }

                // 띄어쓰기 또는 개행 입력
                if (j != N) {
                    sb.append(" ");
                } else {
                    sb.append("\n");
                }
            }
        }
        System.out.print(sb.toString());
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
