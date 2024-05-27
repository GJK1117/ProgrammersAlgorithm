package programmers;

import java.util.*;

public class kjh_혼자놀기의달인 {
    static int[] box;
    public static int solution(int[] cards) {
        int answer = 1;
        //카드가 들어있는 그룹을 넣어줄 우선순위 큐,  많은 카드가 들어있는 그룹이 먼저 오도록 내림차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        //유니온파인드 배열
        box = new int[cards.length+1];
        //이미 사용된 카드 구분하기 위한 배열
        boolean[] visited = new boolean[cards.length+1];
        for(int i=0; i<=cards.length; i++) box[i] = i;

        for(int i=1; i<=cards.length; i++){
            int k = i;
            int cnt = 0;
            if(visited[i]) continue;
            while(true){
                if(visited[k]) break;   //이미 방문한 경우 break
                visited[k] = true;      //방문 처리
                union(k, cards[k-1]);   //해당 위치의 카드 값과 위치 값을 같은 집합으로 묶어주기 위해 union 연산
                k = cards[k-1];         //해당 위치의 카드 값을 다음 위치로 변경 해주기 위해 인덱스 변수 k에 현재 카드 숫자의 값을 대입
                cnt++;                  //같은 그룹의 카드 수를 카운트
            }
            pq.offer(cnt);
        }

        //그룹이 2개 이상일 경우 가장 큰 그룹 2개를 곱하여 리턴
        //아닐 경우 0 리턴
        if(pq.size()>1) {
            for(int i=0; i<2; i++) answer *= pq.poll();
        } else answer = 0;

        return answer;
    }

    //유니온-파인드 메서드
    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y) box[y] = x;
    }

    static int find(int x){
        if(box[x]==x) return x;
        else return box[x] = find(box[x]);
    }

    public static void main(String[] args) {
        int[] cards = {8,6,3,7,2,5,1,4};
        System.out.println(solution(cards));
    }
}
