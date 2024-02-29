import java.util.*;

public class Main {
    static int[][] map;
    static HashSet<Integer> result = new HashSet<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        map = new int[5][5];

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                ArrayList<Integer> array = new ArrayList<>();
                array.add(map[i][j]);
                dfs(0, array, i, j);
            }
        }

        System.out.println(result.size());

    }

    public static void dfs(int count, ArrayList<Integer> numlist, int r, int c) {
        if(count == 5) {
            int sum = 0;
            for (int num : numlist) {
                sum = sum * 10 + num;
            }
            result.add(sum);
            return;
        }


        for(int i = 0; i < 4; i++) {
            if(r+dr[i]>=0 && r+dr[i]<5 && c+dc[i]>=0 && c+dc[i]<5) {
                ArrayList<Integer> a = new ArrayList<>(numlist);
                a.add(map[r+dr[i]][c+dc[i]]);
                dfs(count+1, a, r+dr[i], c+dc[i]);
            }
        }
    }
}
