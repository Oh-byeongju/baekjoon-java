package graph_traversal;

import java.io.*;
import java.util.*;

/**
 * 문제: ABCDE
 * 링크: https://www.acmicpc.net/problem/13023
 * 일자: 2025-11-13
 * 유형: DFS 백트래킹
 *
 * 프로그램 로직
 * 1. DFS로 해결
 * 2. 각 노드에서 연결된 간선을 처리
 * 3. 중요: 노드에 진입했을 때 visit를 체크
 */

public class _13023_dfs {
    static int N, M;
    static List<List<Integer>>graph = new ArrayList<>();
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        // 노드 방문 체크용 배열
        visit = new boolean[N];

        for (int i = 0; i <N; i++) {
            dfs(i, 1);
        }

        print(0); // 재귀 내부에서 종료가 안된경우
    }

    public static void dfs(int start, int depth) {
        visit[start] = true; // 노드에 진입했을때 부터 체크

        if (depth == 5) { // 4개의 연관관계가 이어지면 프로세스 종료
            print(1);
            System.exit(0);
        }

        for (int i = 0; i < graph.get(start).size(); i++) {
            int cur = graph.get(start).get(i);

            if (!visit[cur]) {
                dfs(cur, depth + 1);
            }
        }

        visit[start] = false; // 노드에서 탈출할 때 false 처리
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
