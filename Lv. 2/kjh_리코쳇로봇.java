package programmers;

import java.util.*;

public class kjh_리코쳇로봇 {
    static int Rx, Ry;  //시작점 위치
    static int Gx, Gy;  //도착점 위치
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
    static Queue<Robot> queue = new LinkedList<>(); //bfs 탐색 큐
    public static int solution(String[] board) {
        int answer = -1;    //도착하지 못한다면 -1 리턴
        int xlen = board.length;
        int ylen = board[0].length();
        boolean[][] visited = new boolean[xlen][ylen];
        for(int i=0; i<xlen; i++){
            for(int j=0; j<ylen; j++){
                if(board[i].charAt(j)=='R'){
                    Rx = i;
                    Ry = j;
                }
                if(board[i].charAt(j)=='G'){
                    Gx = i;
                    Gy = j;
                }
            }
        }
        //시작점 시작
        queue.offer(new Robot(Rx, Ry, 0));
        while(!queue.isEmpty()){
            Robot robot = queue.poll();
            //도착점 도착하면 return
            if(robot.x==Gx && robot.y==Gy) return robot.value;
            for(int i=0; i<4; i++){
                int kx = robot.x;
                int ky = robot.y;
                if(kx<0 || kx>=xlen || ky<0 || ky>=ylen) continue;

                /**
                 * ** 핵심 **
                 * 반복문을 사용하여 현재 위치에서 문제 조건에 걸릴때까지 한방향으로 쭉 진행시켜 준다.
                 */
                while(true) {
                    kx += dx[i];
                    ky += dy[i];
                    //다음 위치의 board 값이 'D'거나 범위 밖으로 나갈경우 다시 빼주어 직전 위치값으로 만들어준다.
                    if(kx<0 || kx>=xlen || ky<0 || ky>=ylen || board[kx].charAt(ky)=='D'){
                        kx -= dx[i];
                        ky -= dy[i];
                        break;
                    }
                }
                //그자리 그대로면 continue
                if(visited[kx][ky] || (kx==robot.x && ky==robot.y)) continue;
                //한 방향으로 진행한 위치와 이동횟수를 하나 증가시켜 큐에 넣어줌
                queue.offer(new Robot(kx, ky, robot.value+1));
                visited[kx][ky] = true;
            }
        }
        return answer;
    }

    static class Robot {
        int x;  //x, y 위치
        int y;  //이동 횟수
        int value;

        public Robot(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        System.out.println(solution(board));
    }
}
