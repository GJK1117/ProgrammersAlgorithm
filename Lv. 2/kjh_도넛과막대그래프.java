package programmers;

import java.util.*;

public class kjh_도넛과막대그래프 {
    static int[] answer = new int[4];
    static Map<Integer, List<Integer>> map = new HashMap<>();
    public static int[] solution(int[][] edges) {
        int[] go = new int[1000001];    //나가는 간선 개수
        int[] come = new int[1000001];  //들어오는 간선 개수
        int max = 0;
        //생성된 노드 탐색
        for(int i=0; i<edges.length; i++){
            //키가 존재하면 그대로 추가
            if(map.containsKey(edges[i][0])) map.get(edges[i][0]).add(edges[i][1]);
            //키가 존재하지 않으면 리스트 새로 만들고 추가
            else {
                map.put(edges[i][0], new ArrayList<>());
                map.get(edges[i][0]).add(edges[i][1]);
            }
            go[edges[i][0]]++;  //나가는 간선 개수 증가
            come[edges[i][1]]++;    //들어오는 간선 개수 증가
            //나가는 간선이 제일 많고, 들어오는 간선이 0이 정점이 생성된 노드가 됨
            if(go[edges[i][0]]>=max && come[edges[i][0]]==0){
                answer[0] = edges[i][0];
                max = go[edges[i][0]];
            }
        }
        //생성된 노드와 연결된 노드 탐색
        for(Integer k : map.get(answer[0])) findGraph(k);

        return answer;
    }
    //그래프 찾는 메서드
    static void findGraph(int node){
        int firstNode = node;   //시작 노드 저장
        while(true){
            if(map.containsKey(node)) {
                //나가는 간선이 2개 이상일 경우 8자 모양 그래프 확정
                if (map.get(node).size() > 1) {
                    answer[3]++;
                    return;
                }
                //나가는 간선이 2개 미만일 경우 연결된 다음 노드 검사하기 위해 node 초기화
                //만약 연결된 다음 노드가 시작노드와 같을 경우 도넛 모양 그래프 확정
                else {
                    int n = map.get(node).get(0);
                    map.put(node, new ArrayList<>());
                    if(n==firstNode) {
                        answer[1]++;
                        return;
                    }
                    node = n;
                }
            } else {    //해당 키가 존재하지 않으면 나가는 간선이 없는 노드이므로 막대그래프 확정
                answer[2]++;
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
        System.out.println(Arrays.toString(solution(edges)));
    }
}
