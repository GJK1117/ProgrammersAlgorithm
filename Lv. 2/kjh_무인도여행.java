package programmers;

import java.util.*;

public class kjh_무인도여행 {
    static Queue<Integer> queue = new LinkedList<>();   //탐색할 큐
    static boolean[][] visited; //방문 표시 배열
    public static List<Integer> solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        int xlen = maps.length; //maps 가로 길이
        int ylen = maps[0].length();    //maps 세로 길이
        visited = new boolean[xlen][ylen];
        for(int i=0; i<xlen; i++){
            for(int j=0; j<ylen; j++){
                //X가 아니고 방문하지 않았다면 bfs 수행
                if(maps[i].charAt(j)!='X' && !visited[i][j]) {
                    queue.offer(i);
                    queue.offer(j);
                    visited[i][j] = true;
                    answer.add(bfs(maps, xlen, ylen));
                }
            }
        }
        //결과값 리스트가 비었을경우 -1 추가
        if(answer.isEmpty()) answer.add(-1);
        //결과값 리스트 오름차순 정렬
        Collections.sort(answer);

        return answer;
    }

    static int bfs(String[] maps, int xlen, int ylen){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int res = 0;
        //상하좌우로 이동하며 탐색
        while(!queue.isEmpty()){
            int x = queue.poll();
            int y = queue.poll();
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= xlen || ny <0 || ny >= ylen) continue;
                if(visited[nx][ny] || maps[nx].charAt(ny)=='X') continue;

                queue.offer(nx);
                queue.offer(ny);
                visited[nx][ny] = true;
            }
            //현재 칸의 값 결과값 변수에 저장
            //숫자 형태의 문자열의 경우 char 로 바꾼후 '0'을 빼주면 int 변수로 바로 변환이 가능하다.
            res += (maps[x].charAt(y)-'0');
        }
        return res;
    }

    public static void main(String[] args) {
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        System.out.println(solution(maps));
    }
}
