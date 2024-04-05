package programmers;

import java.util.*;

public class kjh_가장먼노드 {
    static List<List<Integer>> list = new ArrayList<>();    //연결리스트
    static Queue<Integer> q = new LinkedList<>();   //bfs 탐색 큐
    static boolean[][] visited;     //간선 방문 표시
    static int[] degree;    //각 노드의 최단경로 간선 개수 저장할 배열
    public static int solution(int n, int[][] edge) {
        int answer = 0, max = 0;
        degree = new int[n+1];
        visited = new boolean[n+1][n+1];
        Arrays.fill(degree, 50001);     //최대 간선이 50000개 이하이므로 50001로 초기화
        degree[1] = 0;
        for(int i=0; i<=n; i++) list.add(new ArrayList<>());
        for(int i=0; i<edge.length; i++){
            //양방향 연결이므로 연결리스트에 각각 추가
            list.get(edge[i][0]).add(edge[i][1]);
            list.get(edge[i][1]).add(edge[i][0]);
        }
        q.offer(1);     //1번 노드부터 시작
        while(!q.isEmpty()){
            int node = q.poll();
            //연결된 노드를 탐색
            for(Integer k : list.get(node)){
                //이미 지나간 간선이면 continue;
                if(visited[node][k] || visited[k][node]) continue;
                q.offer(k);
                //간선 방문 처리
                visited[node][k] = true;
                visited[k][node] = true;

                //최단 경로를 찾기 위해 대소 비교를 통해 가장 작은 최단경로를 넣어줌
                if(degree[k]>degree[node]+1) degree[k] = degree[node]+1;
            }
        }
        //가장 먼 노드를 찾아주기 위해 가장 먼 노드의 간선 개수 탐색
        //degree가 50001 초기값과 같다는 것은 아예 방문을 안했다는 것이므로 범위를 정해주어 제외해줌
        for(int i=1; i<=n; i++)
            if(degree[i] > max && degree[i] < 50001) max = degree[i];
        //가장 먼 노드의 개수 세기
        for(int i=1; i<=n; i++) if(degree[i]==max) answer++;
        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n, edge));
    }
}
