package programmers;

import java.util.*;

public class kjh_게임맵최단거리 {
    static Queue<Integer> queue = new LinkedList<>();   //bfs 큐
    static boolean[][] visited; //방문 표시 배열
    static int n, m;
    public static int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        return bfs(0, 0, maps);
    }

    static int bfs(int s, int e, int[][] maps) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        queue.offer(s);
        queue.offer(e);
        while(!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            //상하좌우 이동
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                //맵 밖으로 벗어날 경우 continue
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                //막혀있거나 이미 방문했을 경우 continue
                if(maps[nx][ny] == 0 || visited[nx][ny]) continue;

                queue.offer(nx);
                queue.offer(ny);
                visited[nx][ny] = true;
                maps[nx][ny] += maps[x][y]; //막히지 않은 maps의 값 모두 1이므로 다음 경로에 현재 값을 그냥 더해줌
            }
        }
        //상대 진영(도착지점)의 값이 1인 것은 도착하지 못한 경우이므로 -1 리턴
        return (maps[n-1][m-1]==1) ? -1 : maps[n-1][m-1];
    }

    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println(solution(maps));
    }
}
