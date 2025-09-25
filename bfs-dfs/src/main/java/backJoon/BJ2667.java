package backJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* <그림 1>과 같이 정사각형 모양의 지도가 있다.
* 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
* 철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
* 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다.
* 대각선상에 집이 있는 경우는 연결된 것이 아니다.
* <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다.
* 지도를 입력하여 단지수를 출력하고,
* 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오. */

public class BJ2667 {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0 ,0};
    static int cx, cy;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for(int j = 0; j < str.length(); j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !visit[i][j]) {
                    count++;
                    bfs(i, j);
                }
            }
        }

        System.out.println(count);
        Collections.sort(list);
        for(int i : list) {
            System.out.println(i);
        }
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        visit[x][y] = true;
        q.offer(new Node(x, y));

        int cnt = 1;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for(int i = 0; i < 4; i++) {
                cx = node.x + dirX[i];
                cy = node.y + dirY[i];

                if(cx >= 0 && cy >= 0 && cx < N && cy < N
                        && map[cx][cy] == 1 && !visit[cx][cy]) {
                    cnt++;
                    q.offer(new Node(cx, cy));
                    visit[cx][cy] = true;
                }
            }
        }

        list.add(cnt);
    }
}
