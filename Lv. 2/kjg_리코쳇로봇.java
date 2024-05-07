import java.util.*;

// dfs로는 해결이 안되는 문제, bfs로 변경시 바로 풀림
class Solution {
  // 상하좌우 움직임 정의
    private final int[] dr = {-1, 1, 0, 0};
    private final int[] dc = {0, 0, -1, 1};

    public int solution(String[] board) {
      // 시작 위치 찾기
        int[] s = findStart(board);

      // BFS 함수로 정답 찾기
        return bfs(board, s[0], s[1]);
    }

  // 시작 위치 찾는 함수
    private int[] findStart(String[] board) {
        for (int i = 0; i < board.length; i++) {
            int tmp = board[i].indexOf("R");
            if (tmp != -1) {
                return new int[]{i, tmp};
            }
        }
        return null;
    }

  // BFS로 목적지까지 움직임 탐색
    private int bfs(String[] board, int r, int c) {
        int n = board.length;
        int m = board[0].length();
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c, 0});
        visited[r][c] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currR = curr[0];
            int currC = curr[1];
            int currCnt = curr[2];
            
            if (board[currR].charAt(currC) == 'G') {
                return currCnt;
            }
            
            for (int i = 0; i < 4; i++) {
                int[] tmp = {currR, currC};
              // 한 번 움직임의 방향이 정해지면 갈 수 있는 한 계속 이동
                while (isValid(n, m, tmp[0] + dr[i], tmp[1] + dc[i]) &&
                       board[tmp[0] + dr[i]].charAt(tmp[1] + dc[i]) != 'D') {
                    tmp[0] += dr[i];
                    tmp[1] += dc[i];
                }

              // 방문하지 않은 위치라면 큐에 삽입
                if (!visited[tmp[0]][tmp[1]]) {
                    visited[tmp[0]][tmp[1]] = true;
                    queue.offer(new int[]{tmp[0], tmp[1], currCnt + 1});
                }
            }
        }
        
        return -1;
    }
    
    private boolean isValid(int a, int b, int r, int c) {
        return r >= 0 && r < a && c >= 0 && c < b;
    }
}
