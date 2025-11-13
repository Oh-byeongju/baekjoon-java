package graph_traversal;

import java.io.*;
import java.util.*;

/**
 * 문제: 최단경로 (1753)
 * 링크: https://www.acmicpc.net/problem/1753
 * 일자: 2025-11-13
 * 유형: 다익스트라 (우선순위 큐)
 *
 * 프로그램 로직
 * 1. graph[v] : (to, weight) 리스트 저장
 * 2. dist[] : 시작점에서 v까지의 현재 최단 거리
 * 3. 방문 배열 visit[] 로 “최단거리 확정 여부” 체크
 * 4. PriorityQueue 를 사용해 매번 최소 비용 노드 선택
 */

public class _1753_dijkstra {
    static int V, E, K;
    static final int INF = (int)1e9;

    static List<List<int[]>> graph = new ArrayList<>();
    static int[] dist;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        // 그래프 초기화
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 입력 (A → B, 비용 C)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new int[]{B, C});
        }

        dist = new int[V + 1];
        visit = new boolean[V + 1];
        Arrays.fill(dist, INF);

        dijkstra(K);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dist[i] == 1e9) {
                sb.append("INF").append("\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    public static void dijkstra(int start) {
        // 시작 정점 초기화
        dist[start] = 0;

        // 최소 비용 기준 오름차순 큐
        // 주의: 배열 중 두번째 값이 비용에 대한 내용이므로, 잘 생각하기
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[1] - b[1]
        );
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            // 이미 최단거리 확정된 노드는 skip
            if (visit[curNode]) continue;
            visit[curNode] = true;

            // 현재 비용이 dist보다 크면 무의미한 값
            if (curCost > dist[curNode]) continue;

            for (int[] next : graph.get(curNode)) {
                int nextNode = next[0];
                int nextCost = next[1];
                int newCost = curCost + nextCost;

                // 더 짧은 경로 발견 → 갱신
                if (newCost < dist[nextNode]) {
                    dist[nextNode] = newCost;
                    pq.offer(new int[]{nextNode, newCost});
                }
            }
        }
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}