package backJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14888 {
    static int N;
    static int[] arr;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int addCnt, subCnt, mulCnt, divCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        addCnt = Integer.parseInt(st.nextToken());
        subCnt = Integer.parseInt(st.nextToken());
        mulCnt = Integer.parseInt(st.nextToken());
        divCnt = Integer.parseInt(st.nextToken());

        dfs(1, arr[0], addCnt, subCnt, mulCnt, divCnt);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int idx, int cur, int add, int sub, int mul, int div) {
        if(idx == N) {
            min = Math.min(min, cur);
            max = Math.max(max, cur);
            return;
        }

        int x = arr[idx];

        if(add > 0) dfs(idx + 1, cur + x, add - 1, sub, mul, div);
        if(sub > 0) dfs(idx + 1, cur - x, add, sub - 1, mul, div);
        if(mul > 0) dfs(idx + 1, cur * x, add, sub, mul - 1, div);
        if(div > 0) {
            int next;
            if(cur > 0) next = cur / x;
            else next = - ( Math.abs(cur) / x );
            dfs(idx + 1, next, add, sub, mul, div - 1);
        }
    }
}
