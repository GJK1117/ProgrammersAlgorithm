package programmers;

import java.util.*;

public class kjh_더맵게 {
    //스코빌 지수 오름차순으로 정렬되어 들어가도록 우선순위 큐 사용
    static PriorityQueue<Long> scovilleQ = new PriorityQueue<>();
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        for(int s : scoville) scovilleQ.offer(Long.valueOf(s));
        //스코빌지수가 가장 작은 값이 K보다 작을 경우에 반복
        //K 보다 클 경우엔 문제 조건에 충족되므로 세주지 않는다.
        while(scovilleQ.peek()<K) {
            //두개씩 섞다보면 큐에 값이 하나만 남는 경우가 생김
            //이때 이 값이 K보다 작다면 K보다 큰 값이 없으므로 -1
            if(scovilleQ.size()==1) {
                if(scovilleQ.peek() < K) answer=-1;
                break;
            }
            //맨 앞의 두 값 poll
            Long x = scovilleQ.poll();
            Long y = scovilleQ.poll();
            //섞어서 넣어줌
            Long mix = x + 2*y;
            scovilleQ.offer(mix);
            //횟수 증가
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println(solution(scoville, K));
    }
}
