package graph_traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제: 이모티콘
 * 링크: https://www.acmicpc.net/problem/14226
 * 일자: 2025-11-12
 * 유형: BFS
 *
 * 프로그램 로직
 * 1. y: 화면, x: 클립보드
 * 2. 시작 상태 → (1,0), 이동 횟수 0
 * 3. 이동할 때마다 이전 값 +1 로 거리 갱신
 */

public class _14226_bfs {
    static int S;
    static int maxNum;
    static int[][] dist;
    static Queue<int []> q;

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        maxNum = 2000; // S <= 1000 이므로, 두배로 제한

        // 움직인 결과값을 저장
        // 화면을 y(행), 클립보드를 x(열)
        dist = new int[maxNum + 1][maxNum + 1];

        // -1로 초기화 (탐색용)
        for (int i = 0 ; i <= maxNum; i++) {
            Arrays.fill(dist[i], -1);
        }

        // 큐 초기화 후, default 값 삽입
        q = new ArrayDeque<>();
        dist[1][0] = 0; // 화면에 1개, 클립보드 0개 -> 기본값이라 0에서 시작
        q.offer(new int[]{1, 0});

        bfs();
    }

    public static void bfs() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];

            if (y == S) {
                print(dist[y][x]);
                return;
            }

            // 클립보드 복사: (y, x) -> (y, y)
            if (dist[y][y] == -1) {
                dist[y][y] = dist[y][x] + 1;
                q.offer(new int[]{y, y});
            }

            // 화면 붙여넣기: (y, x) -> (y + x, x)
            // 1. 복사된 것이 하나 이상
            // 2. 화면 + 클립보드 값이 최대값을 안 넘어야함
            if (x > 0 && y + x <= maxNum && dist[y + x][x] != 0) {
                dist[y + x][x] = dist[y][x] + 1;
                q.offer(new int[]{y + x, x});
            }

            // 이모티콘 삭제: (y, x)
            // 1. 화면에 이모티콘이 하나 이상 있어야함
            if (y > 0 && dist[y - 1][x] == -1) {
                dist[y-1][x] = dist[y][x] + 1;
                q.offer(new int[]{y-1, x});
            }
        }
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
