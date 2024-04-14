package programmers;

import java.util.*;

public class kjh_숫자변환하기 {
    public static int solution(int x, int y, int n) {
        //최소연산횟수(=최단거리) 를 구하는 것이기 때문에 dfs가 아닌 bfs 사용
        int answer = bfs(x, y, n);
        //정답변수의 값이 Integer.MAX_VALUE 이면 y에 도달하지 못한 것이므로 -1 리턴
        return (answer==Integer.MAX_VALUE) ? -1 : answer;
    }

    static int bfs(int x, int y, int n){
        Queue<Node> queue = new LinkedList<>();
        //boolean[] visited 를 활용해 ture ,false 로 방문처리를 해주어도 된다.
        //이렇게 선언한 이유는 int 배열을 사용하여 각 숫자까지 도달하는데 걸리는 최소연산횟수를 바로 저장하여
        //cost[y] 즉 숫자 y에 도달했을때 최소연산횟수를 바로 리턴하기 위함이다.
        int[] cost = new int[y+1];
        Arrays.fill(cost, Integer.MAX_VALUE);   //값을 비교하여 최소 연산횟수를 넣어주어야 하므로 최댓값으로 초기화
        queue.offer(new Node(x, 0));
        cost[x] = 0;
        while(!queue.isEmpty()){
            Node now = queue.poll();
            //y일 경우 break
            //모든 경우가 아닌 최소연산 횟수를 구하는 문제
            //y에 먼저 도착하면 그게 답이므로 바로 break 해준다.
            if(now.x==y) break;
            //x+n 이 y보다 작거나 같고 최소연산횟수가 더 작다면 큐에 넣어준다.
            //x+n, x*2, x*3 세개의 연산에 대해 모두 조건을 따져서 탐색한다.
            if(now.x+n <= y && now.cnt+1<cost[now.x+n]){
                queue.offer(new Node(now.x+n, now.cnt+1));
                cost[now.x+n] = now.cnt+1;
            }
            if(now.x*2 <= y && now.cnt+1<cost[now.x*2]){
                queue.offer(new Node(now.x*2, now.cnt+1));
                cost[now.x*2] = now.cnt+1;
            }
            if(now.x*3 <= y && now.cnt+1<cost[now.x*3]){
                queue.offer(new Node(now.x*3, now.cnt+1));
                cost[now.x*3] = now.cnt+1;
            }
        }
        //y가 되었을 경우 최소연산횟수 리턴
        return cost[y];
    }

    static class Node{
        int x;      //연산 숫자
        int cnt;    //연산 횟수

        public Node(int x, int cnt){
            this.x = x;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) {
        int x=10, y=40, n=5;
        System.out.println(solution(x, y, n) );
    }
}

/**
 * ++ dfs로 접근 했을 때 내가 작성한 코드(시간초과 ㅠㅠ)
 * y가 될 수 있는 모든 연산횟수를 구하는 문제가 아닌
 * 이문제는 최소연산 횟수를 구하는 문제이므로 bfs 를 사용하여서 풀이
 */
class Solution2 {
    static int answer = Integer.MAX_VALUE;
    public int solution(int x, int y, int n) {
        int cnt = 0;
        dfs(x, y, n, cnt);
        return (answer==Integer.MAX_VALUE) ? -1 : answer;
    }

    static void dfs(int x, int y, int n, int cnt){
        if(x>y) return;
        if(x==y) {
            if(cnt <= answer) answer = cnt;
            return;
        }
        for(int i=0; i<3; i++){
            if(i==2) dfs(x+n, y, n, cnt+1);
            else {
                dfs(x*(i+2), y, n, cnt+1);
            }
        }
    }
}
