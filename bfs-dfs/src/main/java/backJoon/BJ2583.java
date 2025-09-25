package backJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2583 {
    static int M, N, K, x1, y1, x2, y2, cx, cy;
    static int[][] map;
    static boolean[][] visit;
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dirX = {0, 0, -1 ,1};
    static int[] dirY = {-1, 1, 0 ,0};
    static List<Integer> list;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visit = new boolean[M][N];
        list = new ArrayList<>();

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            for(int y = y1; y < y2; y++) {
                for(int x = x1; x < x2; x++) {
                    map[y][x] = 1;
                }
            }
        }

        int count = 0;

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 0 && !visit[i][j]) {
                    count++;
                    bfs(i, j);
                }
            }
        }

        Collections.sort(list);

        System.out.println(count);

        StringBuilder sb = new StringBuilder();
        for(int i : list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        visit[x][y] = true;
        q.offer(new Node(x, y));

        int cnt = 1;

        while(!q.isEmpty()) {
            Node node = q.poll();

            for(int i = 0; i < 4; i++) {
                cx = node.x + dirX[i];
                cy = node.y + dirY[i];

                if(cx >= 0 && cy >= 0 && cx < M && cy < N
                    && map[cx][cy] == 0 && !visit[cx][cy]) {
                    cnt++;
                    q.offer(new Node(cx, cy));
                    visit[cx][cy] = true;
                }
            }
        }
        list.add(cnt);
    }
}
