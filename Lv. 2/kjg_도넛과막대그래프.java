import java.util.*;

public class kjg_도넛과막대그래프 {
    public int[] solution(int[][] edges) {
        // HashMap을 이용한 인접리스트
        Map<Integer, List<Integer>> idx_map = new HashMap<>();
        // 각 노드의 진입되는 간선 수 저장, 진입 차수라고 함
        int[] cnt = new int[1000001];
        // 없는 노드는 -1로 정의, 노드가 존재할 경우 0부터 시작
        Arrays.fill(cnt, -1);
        // 정답 배열 선언
        int[] answer = new int[4];

        // edges를 통한 인접리스트 생성
        for(int[] edge : edges){
            // idx_map에 edge[0]이 없을 경우 새로운 ArrayList 생성
            if(!idx_map.containsKey(edge[0])){
                idx_map.put(edge[0], new ArrayList<>());
            }
            // idx_map에 edge[1]이 없을 경우 새로운 ArrayList 생성
            if(!idx_map.containsKey(edge[1])){
                idx_map.put(edge[1], new ArrayList<>());
            }
            // edge[0]에 edge[1] 추가
            idx_map.get(edge[0]).add(edge[1]);

            // cnt에 시작, 도착 노드 모두 -1인 경우 0으로 변경
            if(cnt[edge[0]] == -1) cnt[edge[0]] = 0;
            if(cnt[edge[1]] == -1) cnt[edge[1]] = 0;

            // 도착 노드에 진입 차수 증가
            cnt[edge[1]]++;
        }

        // cnt 배열 순회
        for(int i = 1; i <= 1000000; i++){
            // 진입 차수가 0이면서, 인접리스트의 크기가 2 이상인 경우(생성 노드의 기본 조건)
            if(cnt[i] == 0 && idx_map.get(i).size() >= 2){
                // answer[0]을 i로 초기화 후 break
                answer[0] = i;
                break;
            }
        }

        // answer[0]을 시작으로 인접리스트 순회
        List<Integer> tmp = idx_map.get(answer[0]);
        for(int i : tmp){
            // 순회할 때 쓰일 리스트 선언
            List<Integer> now = idx_map.get(i);
            while(true){
                // 현재 위치에서 연결된 노드가 없다면 막대모양 그래프의 마지막 노드
                if(now.isEmpty()) {
                    answer[2]++;
                    break;
                }
                // 현재 위치에서 연결된 노드가 2개라면 8자모양 그래프
                else if(now.size() == 2) {
                    answer[3]++;
                    break;
                }
                // 현재 위치에서 연결된 노드가 출발한 노드인 경우 도넛모양 그래프
                else if(now.get(0) == i) {
                    answer[1]++;
                    break;
                }
                // 다음 노드를 기준으로 인접리스트 순회
                now = idx_map.get(now.get(0));
            }
        }

        // 결과 반환
        return answer;
    }
}
