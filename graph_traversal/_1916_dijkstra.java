package graph_traversal;

import java.io.*;
import java.util.*;

/**
 * 문제: 최소비용 구하기
 * 링크: https://www.acmicpc.net/problem/1916
 * 일자: 2025-11-13
 * 유형: 다익스트라 (우선순위 큐)
 *
 * 프로그램 로직
 * 1. graph[v] : (to, weight) 리스트 저장
 * 2. dist[] : 시작점에서 v까지의 현재 최단 거리
 * 3. 방문 배열 visit[] 로 “최단거리 확정 여부” 체크
 * 4. PriorityQueue 를 사용해 매번 최소 비용 노드 선택
 */

public class _1916_dijkstra {
    static int N, M;
    static int start, end;
    static int INF = (int)1e9;
    static List<List<int[]>> graph;
    static int[] dist;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시
        M = Integer.parseInt(br.readLine()); // 버스

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 입력 (A → B, 비용 C)
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new int[]{B, C});
        }

        // 출발지와 도착지 변수 할당
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        visit = new boolean[N + 1];
        Arrays.fill(dist, INF);

        dijkstra(start);
        print(dist[end]);
    }

    public static void dijkstra(int start) {
        // 출발지 조정
        dist[start] = 0;

        // 최소 비용 기준 오름차순 큐
        // 주의: 배열 중 두번째 값이 비용에 대한 내용이므로, 잘 생각하기
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[1] - b[1]
        );
        pq.offer(new int[]{start, 0}); // 노드, 간선 순서

        while(!pq.isEmpty()) {
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