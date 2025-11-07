package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1003_fibo {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // N이 최대 40이므로 배열 선언 후, 초기값 세팅
        int [][] arr = new int [41][2];
        // 0일 경우
        arr[0][0] = 1;
        arr[0][1] = 0;
        // 1일 경우
        arr[1][0] = 0;
        arr[1][1] = 1;

        // 2 ~ 40 까지 세팅
        for (int i = 2; i <= 40; i++) {
            arr[i][0] = arr[i-2][0] + arr[i-1][0];
            arr[i][1] = arr[i-2][1] + arr[i-1][1];
        }

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            print(arr[N][0] + " " + arr[N][1]);
        }
    }

    public static void print(Object msg) {
        System.out.println(msg);
    }
}