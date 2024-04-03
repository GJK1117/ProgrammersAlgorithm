package programmers;

import java.util.*;

public class kjh_석유시추 {
    static Queue<Integer> q = new LinkedList<>();   //bfs 탐색 큐
    static boolean[][] visited; //방문표시 배열
    static int[] sum;   //각 열의 총 석유량 저장 배열
    static int n, m;    //land 가로 세로 길이
    public static int solution(int[][] land) {
        int answer = 0;
        n=land.length;
        m=land[0].length;
        visited = new boolean[n][m];
        sum = new int[m];   //열 크기로 초기화
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                //방문하지 않았고, 석유가 있는 땅이면 bfs 수행
                if(!visited[i][j] && land[i][j]==1) bfs(i, j, land);
            }
        }
        //가장 큰 석유량 찾은 후 리턴
        for(int i=0; i<m; i++) if(sum[i]>=answer) answer = sum[i];
        return answer;
    }

    static void bfs(int a, int b, int[][] land){
        //현재 탐색되는 석유 덩어리를 시추할 수 있는 열을 저장할 집합
        //세로로 이어진 칸의 경우에는 열 인덱스 값이 중복되므로 중복을 없애주기 위해 Set 자료구조 사용
        Set<Integer> tempSet = new HashSet<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int res = 0;    //석유 덩어리 값 저장 배열
        q.offer(a); q.offer(b);
        visited[a][b] = true;
        while(!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();
            res += land[x][y];  //현재 칸의 석유량을 더해준다.
            tempSet.add(y);     //현재 열은 석유를 시추할 수 있으므로 Set 에 추가
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(visited[nx][ny] || land[nx][ny]==0) continue;

                q.offer(nx);
                q.offer(ny);
                visited[nx][ny] = true;
            }
        }
        Iterator<Integer> it = tempSet.iterator();
        while(it.hasNext()){
            Integer k = it.next();
            sum[k] += res;  //Set를 반복하며 총 석유량 저장 배열에 현재 석유 덩어리 값 더해줌
        }
    }

    public static void main(String[] args) {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        System.out.println(solution(land));
    }
}
