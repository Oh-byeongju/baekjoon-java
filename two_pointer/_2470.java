package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제: 두 용액
 * 링크: https://www.acmicpc.net/problem/2470
 * 일자: 2025-11-09
 * 유형: 투 포인터
 *
 * 프로그램 로직
 * 1. 변수 초기화
 * 2. 배열 정렬
 * 3. while 문을 사용하여 투 포인터 로직 수행
 */
public class _2470 {
    public static void main(String [] args) throws IOException {
        // 1. 변수 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] arr = new int[N];
        int result = 0;
        int result2 = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 배열 정렬
        Arrays.sort(arr);

        // 3. while 문을 사용하여 투 포인터 로직 수행
        int left = 0;
        int right = N - 1;
        int attrValue = Integer.MAX_VALUE;

        // 기준값을 Integer.MAX_VALUE로 초기화한다.
        // 투 포인터 로직에서 두 포인터(left, right)의 합의 절대값과 현재 기준값을 비교한다.
        // 만약 절대값이 더 작으면 기준값을 갱신하고, 해당 두 수를 결과값으로 저장한다.
        // 두 수의 합이 음수이면 합을 0에 더 가깝게 만들기 위해 left 포인터를 오른쪽으로 이동한다.
        // 두 수의 합이 양수이면 right 포인터를 왼쪽으로 이동한다.
        while (left < right) {
            int mid = arr[left] + arr[right];

            if (Math.abs(mid) < attrValue) {
                attrValue = Math.abs(mid);
                result = arr[left];
                result2 = arr[right];
            }

            if (mid < 0) {
                left++;
            } else {
                right--;
            }
        }

        print(result + " " + result2);
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
