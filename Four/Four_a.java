package Four;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Four_a {
    static class State {
        int x, y;
        String keys;

        State(int x, int y, String keys) {
            this.x = x;
            this.y = y;
            this.keys = keys;
        }
    }

    public static int shortestPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Set<Character> keys = new HashSet<>();
        Map<Character, int[]> doors = new HashMap<>();
        int start_x = -1, start_y = -1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cell = grid[i][j];
                if (cell == 'S') {
                    start_x = i;
                    start_y = j;
                } else if ('a' <= cell && cell <= 'z') {
                    keys.add(cell);
                } else if ('A' <= cell && cell <= 'Z') {
                    doors.put(cell, new int[] { i, j });
                }
            }
        }

        List<Character> keysList = new ArrayList<>(keys);
        int[] minDistance = { Integer.MAX_VALUE };
        dfs(grid, start_x, start_y, keysList, doors, new boolean[m][n], "", 0, minDistance);

        return minDistance[0] == Integer.MAX_VALUE ? -1 : minDistance[0];
    }

    private static void dfs(char[][] grid, int x, int y, List<Character> keys, Map<Character, int[]> doors,
            boolean[][] visited, String collectedKeys, int distance, int[] minDistance) {
        if (distance >= minDistance[0])
            return;

        visited[x][y] = true;

        for (int[] dir : new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && !visited[nx][ny]) {
                char cell = grid[nx][ny];

                if (cell == 'P' || cell == 'S') {
                    dfs(grid, nx, ny, keys, doors, visited, collectedKeys, distance + 1, minDistance);
                } else if ('a' <= cell && cell <= 'z') {
                    String newCollectedKeys = collectedKeys + cell;
                    if (newCollectedKeys.length() == keys.size()) {
                        minDistance[0] = Math.min(minDistance[0], distance + 1);
                    } else {
                        dfs(grid, nx, ny, keys, doors, visited, newCollectedKeys, distance + 1, minDistance);
                    }
                } else if ('A' <= cell && cell <= 'Z') {
                    char key = Character.toLowerCase(cell);
                    if (collectedKeys.indexOf(key) != -1) {
                        dfs(grid, nx, ny, keys, doors, visited, collectedKeys, distance + 1, minDistance);
                    }
                }
            }
        }

        visited[x][y] = false;
    }

    public static void main(String[] args) {
        char[][] grid = {
                { 'S', 'P', 'q', 'P', 'P' },
                { 'W', 'W', 'W', 'P', 'W' },
                { 'r', 'P', 'Q', 'P', 'R' }
        };
        System.out.println(shortestPath(grid));
    }
}