import java.util.*;

public class kjg_명예의전당1 {
    public int[] solution(int k, int[] score) {
        // 우선순위 큐 사용
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 정답 배열 선언
        int[] answer = new int[score.length];
        // 정다 배열 인덱스 변수 선언
        int idx = 0;
        // score 배열 순회
        for(int today : score){
            // pq에 값 추가
            pq.add(today);
            // k개를 넘어갔다면 가장 작은 값 삭제
            if(pq.size() == k + 1) pq.remove();

            // 맨 앞의 값이 가장 작은 값이므로 반환, peek()를 사용할 때 pq가 비어있는 지 확인 필요
            if(!pq.isEmpty()) {
                answer[idx++] = pq.peek();
            }
        }

        // 정답 반환
        return answer;
    }
}
