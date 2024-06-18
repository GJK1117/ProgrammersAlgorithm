package programmers;

import java.util.*;

public class kjh_주식가격 {
    /**
     * 몇 초동안 떨어지지 않았는지 계산해주기 위해 우선순위 큐를 활용하였다.
     * prices 배열의 각 값의 인덱스를 현재 second(초)라고 하고 그 위치의 값을 주식가격이라고 가정하고
     * 인덱스와 해당 위치의 값을 Price 클래스를 만들어 객체를 생성해 우선순위 큐에 넣어주었다.
     * 이때 가격이 떨이지지 않았는지 판단해주기 위해 내림차순으로 정렬해주었다.
     * 이제 prices 배열을 처음부터 하나씩 순회하며 해당 값을 Price 객체로 만들어 큐에 넣어준다.
     * 이때 가격이 떨어졌는지 확인해주기 위해 내림차순으로 정렬된 우선순위 큐에서 가장 앞의 값과 현재 주식가격 값을 비교해준다.
     * 현재 주식가격이 더 작다면 이미 큐에 저장된(지난 주식가격) 주식 가격보다 떨어진 것이므로 큐에서 poll 메소드로 제거를 해주고,
     * 현재 second 에서 poll 된 객체의 위치 값을 빼주어 몇 초 동안 떨어지지 않았는지 구해준다. 그리고 이 값을 정답 배열에 저장해준다.
     * 이 과정을 모두 마친 후 우선순위 큐에 남아있는 Price 객체가 있을 수도 있으므로 한번도 큐를 순회하며 위와 동일한 과정으로 계산해준다.
     */
    static PriorityQueue<Price> pq = new PriorityQueue<>();
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int second = 0;
        for(second=0; second<prices.length; second++){
            //현재 시간의 주식 가격 객체 생성
            Price price = new Price(second, prices[second]);
            while(!pq.isEmpty()){
                //현재 주식가격이 떨어 졌으면 poll 해준 후 몇 초동안 떨어지지 않았는지 계산해준 후 정답 배열에 저장
                //몇 초동안 떨어지지 않았는지 구해주기 위해 각 객체의 현재 second 에서 각 객체의 second(초) 값을 빼준다.
                if(price.cost >= pq.peek().cost) break;
                Price p = pq.poll();
                answer[p.idx] = second-p.idx;
            }
            pq.offer(price);
        }
        //위의 for문에 의해 second가 마지막에 하나 증가하므로 하나 빼줘야한다.
        second--;
        //prices 배열의 순회가 끝나고 우선순위 큐에 남아있는 값을 모두 계산(계산 방법은 위와 동일)
        while(!pq.isEmpty()){
            Price p = pq.poll();
            answer[p.idx] = second-p.idx;
        }

        return answer;
    }

    //특정 시간의 주식 가격 값을 저장할 클래스
    //주식 가격이 큰 순서로 우선순위 큐에 넣어주기 위해 Comparable 사용
    static class Price implements Comparable<Price>{
        int idx;    //second(초) 값
        int cost;   //주식 가격

        public Price(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        //주식 가격 순으로 내림차순 정렬
        @Override
        public int compareTo(Price p){
            return (this.cost >= p.cost) ? -1 : 1;
        }
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        System.out.println(Arrays.toString(solution(prices)));
    }
}
