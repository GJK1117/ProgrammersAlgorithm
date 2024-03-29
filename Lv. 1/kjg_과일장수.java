import java.util.*;

public class kjg_과일장수 {
    public int solution(int k, int m, int[] score) {
        // 우선순위 큐로 내림차순으로 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // 정답 변수 선언
        int answer = 0;
        // score를 pq에 추가
        for(int i : score) {
            pq.add(i);
        }

        // m개씩 묶어서 계산
        for(int i = 0; i < score.length / m; i++){
            // 최대 값 지정
            int min = k;
            // m개씩 묶어서 최소값 찾기
            for(int j = 0; j < m; j++){
                int tmp = pq.poll();
                if(min > tmp) min = tmp;
            }
            // 최소값 * m을 합산
            answer += min * m;
        }

        // 결과 반환
        return answer;
    }
}
