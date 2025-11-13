package graph_traversal;

import java.io.*;
import java.util.*;

/**
 * 문제: 플로이드 2 (11780)
 * 링크: https://www.acmicpc.net/problem/11780
 * 일자: 2025-11-14
 * 유형: 플로이드-워셜 (모든 쌍 최단 경로 + 경로 복원)
 *
 * 프로그램 로직
 * 1. dist[i][j] : i -> j 최소 비용,
 * 2. next[i][j] : i -> j 갈 때, i 다음에 가야 할 도시
 * 3. k(거쳐가는 도시), i(출발 도시), j(도착 도시) 순으로 for문 구현
 * 4. 값을 비교할 때는 i → k → j 가 비싼순으로 조건문 설정
 */

public class _11780_floyd {
    static int N, M;
    static final int INF = (int)1e9;
    static int[][] dist;
    static int[][] next;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N+1][N+1];
        next = new int[N+1][N+1];

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
                next[from][to] = to;
            }
        }

        // 플로이드 워셜 진행
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    int newCost = dist[i][k] + dist[k][j];

                    if (newCost < dist[i][j]) {
                        dist[i][j] = newCost;
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        // 1. 최단거리 출력
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

        // 2. 경로 출력
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j || next[i][j] == 0) {
                    // 자기 자신이거나, 갈 수 없는 경우
                    sb.append(0).append("\n");
                } else {
                    List<Integer> path = getPath(i, j);
                    sb.append(path.size()).append(" ");
                    for (int v : path) {
                        sb.append(v).append(" ");
                    }
                    sb.append("\n");
                }
            }
        }

        System.out.print(sb.toString());
    }

    // i -> j 최단 경로를 next 배열을 사용해 복원
    static List<Integer> getPath(int i, int j) {
        List<Integer> path = new ArrayList<>();

        int cur = i;    // 출발지
        path.add(cur);  // 자신을 삽입

        while (cur != j) {
            cur = next[cur][j];
            path.add(cur);
        }

        return path;
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}