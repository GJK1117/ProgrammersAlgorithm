package programmers;

import java.util.*;

public class kjh_아이템줍기 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    //기존 matrix 50x50 크기이지만 ㄷ 자 테두리를 해결하기 위해 좌표값을 2배 하여 처리한다.
    //또한, 아이템까지의 최단경로를 탐색할 때 현재 위치가 테두리인지만 판단하고 matrix 범위는 검사해주지 않기 때문에
    //런타임 에러를 피하기 위해 크기를 101이 아닌 102로 설정해주어 여백 지형을 하나 더 만들어준다.
    static int[][] matrix = new int[102][102];
    static boolean[][] visited;
    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<rectangle.length; i++){
            //각 사각형마다 테두리를 구분해주기 위해 bfs를 수행해준다.
            //이때 테두리가 ㄷ 자 모양일 경우 피해주기 위해 각 좌표를 2배해준다.
            bfs(rectangle[i][0]*2,rectangle[i][1]*2,rectangle[i][2]*2,rectangle[i][3]*2);
        }
        //이제 사각형의 테두리를 따라 아이템을 주울 수 있는 최단경로를 구한다.
        //여기서도 ㄷ 자 모양의 테두리를 피해주기 위해 좌표값을 2배해준다.
        visited = new boolean[101][101];
        queue.offer(characterX*2);
        queue.offer(characterY*2);
        visited[characterX*2][characterY*2] = true;
        while(!queue.isEmpty()){
            int x = queue.poll();
            int y = queue.poll();
            //아이템에 도달할 경우 탐색 종료
            if(x==itemX*2 && y==itemY*2) break;
            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                //위치가 테두리가 아니고 이미 방문하였다면 continue
                if(matrix[nx][ny]!=1 || visited[nx][ny]) continue;

                queue.offer(nx);
                queue.offer(ny);
                visited[nx][ny] = true;
                matrix[nx][ny] += matrix[x][y];
            }
        }
        //탐색할땐 좌표값을 두배하였으므로 경로도 2배가 되어있음
        //정답을 리턴할땐 2로 나눈 값을 리턴해준다.
        return (matrix[itemX*2][itemY*2]-1)/2;
    }

    static void bfs(int sx, int sy, int ex, int ey){
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[102][102];
        queue.offer(sx);
        queue.offer(sy);
        visited[sx][sy] = true;
        while(!queue.isEmpty()){
            int x = queue.poll();
            int y = queue.poll();
            //각 좌표가 사각형의 테두리일경우 1로 초기화
            if((x == sx || x == ex || y == sy || y == ey)){
                //만약 현재 사각형의 테두리가 다른 사각형 안에 위치하는 경우는 제외해준다.
                if(matrix[x][y]!=-1 && matrix[x][y]==0) matrix[x][y]=1;
            }
            //사각형 내부는 -1로 초기화
            else matrix[x][y] = -1;
            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                //검사하는 위치가 사각형 범위 밖이거나 이미 방문했을 경우 continue
                if(nx<sx || nx>ex || ny<sy || ny>ey || visited[nx][ny]) continue;
                queue.offer(nx);
                queue.offer(ny);
                visited[nx][ny] = true;
            }
        }
    }

    public static void main(String[] args) {
        int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int characterX = 1, characterY = 3, itemX = 7, itemY = 8;
        System.out.println(solution(rectangle, characterX, characterY, itemX, itemY));
    }
}
