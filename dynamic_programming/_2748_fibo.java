package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2748_fibo {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // N이 최대 90이므로 배열 선언 후, 초기값 세팅
        long [] arr = new long[91];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i <= N; i++) {
            arr[i] = arr[i - 2] + arr[i - 1];
        }

        print(arr[N]);
    }

    public static void print(Object msg) {
        System.out.println(msg);
    }
}