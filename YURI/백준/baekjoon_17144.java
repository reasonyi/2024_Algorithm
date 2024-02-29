import java.util.*;

public class Main {

    static int R;
    static int C;
    static int T;
    static int[][] map;
    static ArrayList<Point> vacuum = new ArrayList<>();
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        T = sc.nextInt();

        map = new int[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == -1)
                    vacuum.add(new Point(i, j));
            }
        }

        for(int i = 0; i < T; i++) {
            spread();
            cycle();
        }

        result = 0;

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] != -1)
                    result += map[i][j];
            }
        }
        System.out.println(result);
    }

    public static void spread() {
        int[][] spreadMap = new int[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] > 4) { // 확산이 이루어짐.
                    int amount = map[i][j]/5;
                    int dir = 0;
                    for(int k = 0; k < 4; k++) {    // 상하좌우 네 방향 확인 후 확산됨.
                        if(i+dr[k]>=0 && i+dr[k]<R && j+dc[k]>=0 && j+dc[k]<C && map[i+dr[k]][j+dc[k]]!=-1) {
                            spreadMap[i+dr[k]][j+dc[k]] += amount;
                            dir++;
                        }
                    }
                    spreadMap[i][j] += map[i][j] - amount*dir;
                }
                else
                    spreadMap[i][j] += map[i][j];
            }
        }
        map = Arrays.copyOf(spreadMap, spreadMap.length);
    }

    public static void cycle() {
        int topR = 0, topC = 0;
        int bottomR = 0, bottomC = 0;
        int beforeTop = 0, beforeBottom = 0, afterTop = 0, afterBottom = 0;

        topR = vacuum.get(0).r;

        bottomR = vacuum.get(1).r;

        // 왼쪽에서 오른쪽으로 순환
        for(int i = 1; i < C; i++) {
            // 피봇 이동.
            topC = i; bottomC = i;
            // 현재 값을 다음에 넘기기 위해 저장.
            afterTop = map[topR][topC];
            afterBottom = map[bottomR][bottomC];
            // 이전 저장된 값을 가져와 현재 칸에 넣기.
            map[topR][topC] = beforeTop;
            map[bottomR][bottomC] = beforeBottom;

            beforeTop = afterTop;
            beforeBottom = afterBottom;
        }

        // 아래에서 위로 순환
        for(int i = topR-1; i >= 0; i--) {
            topR = i;
            afterTop = map[topR][topC];
            map[topR][topC] = beforeTop;
            beforeTop = afterTop;
        }

        // 위에서 아래로 순환
        for(int i = bottomR+1; i < R; i++) {
            bottomR = i;
            afterBottom = map[bottomR][bottomC];
            map[bottomR][bottomC] = beforeBottom;
            beforeBottom = afterBottom;
        }

        // 오른쪽에서 왼쪽으로 순환
        for(int i = C-2; i >=0; i--) {
            topC = i; bottomC = i;
            afterTop = map[topR][topC];
            afterBottom = map[bottomR][bottomC];
            map[topR][topC] = beforeTop;
            map[bottomR][bottomC] = beforeBottom;
            beforeTop = afterTop;
            beforeBottom = afterBottom;
        }

        // 위에서 아래로 순환
        for(int i = topR+1; i < vacuum.get(0).r; i++) {
            topR = i;
            afterTop = map[topR][topC];
            map[topR][topC] = beforeTop;
            beforeTop = afterTop;
        }

        // 아래에서 위로 순환
        for(int i = bottomR-1; i > vacuum.get(1).r; i--) {
            bottomR = i;
            afterBottom = map[bottomR][bottomC];
            map[bottomR][bottomC] = beforeBottom;
            beforeBottom = afterBottom;
        }
    }
}

class Point {
    int r;
    int c;
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
