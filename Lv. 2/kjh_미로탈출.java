package programmers;

import java.util.*;

public class kjh_미로탈출 {
    static Queue<Node> queue = new LinkedList<>();
    static String[][] maze;
    static int[][] time;
    static boolean[][] visited;
    static boolean lever = false;
    static boolean exit = false;
    static int sx = 0, sy = 0;
    static int lx = 0, ly = 0;
    static int ex = 0, ey = 0;
    public static int solution(String[] maps) {
        int answer = -1;
        maze = new String[maps.length][maps[0].length()];
        time = new int[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];
        for(int i=0; i<maps.length; i++){
            String[] m = maps[i].split("");
            for(int j=0; j<m.length; j++) {
                maze[i][j] = m[j];
                //각 위치 인덱스 저장
                if(maze[i][j].equals("S")) {
                    sx = i;
                    sy = j;
                    visited[i][j] = true;
                }
                else if(maze[i][j].equals("L")) {
                    lx = i;
                    ly = j;
                }
                else if(maze[i][j].equals("E")) {
                    ex = i;
                    ey = j;
                }
            }
        }
        //시작 -> 레버
        bfs(sx, sy, maps.length, maps[0].length(), "L");
        //레버를 지나면 레버 -> 도착
        if(lever) {
            visited = new boolean[maps.length][maps[0].length()];
            visited[lx][ly] = true;
            queue.clear();
            //레버 -> 도착
            bfs(lx, ly, maps.length, maps[0].length(), "E");
        }
        if(lever && exit) {
            answer = time[ex][ey];
        } else {
            answer = -1;
        }
        return answer;
    }

    //bfs 로 최단 경로 탐색
    static void bfs(int start, int end, int x_len, int y_len, String bp){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        queue.offer(new Node(start, end));
        while(!queue.isEmpty()){
            Node now = queue.poll();
            //레버에 도착했을 경우 true
            if(bp.equals("L") && visited[lx][ly]) {
                lever = true;
                return;
            }
            //도착했을 경우 true
            if(bp.equals("E") && visited[ex][ey]) {
                exit = true;
                return;
            }
            for(int i=0; i<4; i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                //미로 밖으로 나가거나 방문했거나 X 인경우 제외
                if(nx<0 || nx>=x_len || ny<0 || ny>=y_len || visited[nx][ny] || maze[nx][ny].equals("X")) continue;
                time[nx][ny] = time[now.x][now.y] + 1;
                queue.offer(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }
    //현재 위치 x, y
    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        System.out.println(solution(maps));
    }
}
