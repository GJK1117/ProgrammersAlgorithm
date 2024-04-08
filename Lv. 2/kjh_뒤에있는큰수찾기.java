package programmers;

import java.util.*;

public class kjh_뒤에있는큰수찾기 {
    public static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        int len = numbers.length;
        //큰수를 찾지 못한 수들을 넣어둘 우선순위 큐 -> 오름차순 정렬
        PriorityQueue<Number> pq = new PriorityQueue<>();
        Arrays.fill(answer, -1);    //못찾으면 -1 넣어주기 위해 미리 -1로 초기화

        for(int i=0; i<len-1; i++){
            //바로 뒤의 수가 큰수이면 바로 추가
            if(numbers[i] < numbers[i+1]) answer[i] = numbers[i+1];
            //큰수가 아니면 우선순위 큐에 추가
            else pq.offer(new Number(numbers[i], i));
            //큰수를 찾지 못한 수가 있는 경우 현재 비교하고 있는 수와 비교하여 작을 경우 결과값에 넣어준다.
            while(!pq.isEmpty()){
                //오름차순으로 정렬되어있으므로 가장 앞에 있는 수가 현재 비교하는 수보다 크면 큐에 있는 모든 수는 모두 크므로 break
                if(pq.peek().value >= numbers[i+1]) break;
                Number n = pq.poll();
                answer[n.idx] = numbers[i+1];
            }
        }
        return answer;
    }

    static class Number implements Comparable<Number>{
        int value;
        int idx;

        public Number(int value, int idx){
            this.value = value;
            this.idx = idx;
        }

        @Override
        public int compareTo(Number o){
            //value 값 기준으로 오름차순 정렬
            return (this.value >= o.value) ? 1 : -1;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {2, 3, 3, 5};
        System.out.println(Arrays.toString(solution(numbers)));
    }
}
