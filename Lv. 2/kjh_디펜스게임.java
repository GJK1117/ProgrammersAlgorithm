package programmers;

import java.util.*;

public class kjh_디펜스게임 {
    //무적권을 사용할 수 있는 적 후보들(가장 많은 수 먼저 무적권을 사용해주기 위해 내림차순 정렬)
    static PriorityQueue<Integer> candidate = new PriorityQueue<>(Collections.reverseOrder());
    public static int solution(int n, int k, int[] enemy) {
        int len = enemy.length;
        int round = 0;  //라운드 수
        for(round=0; round<len; round++) {
            if(n-enemy[round]<0) {
                if(k==0) break;     //남은 병사가 없고 무적권도 없을 경우 break
            }
            candidate.offer(enemy[round]);  //무적권을 사용할 수 있으므로 후보 큐에 넣어준다.
            if(n-enemy[round]<0){
                n+=candidate.poll();    //남은 병사가 없을 경우 무적권을 사용해주고 남은 병사에 적의 수만큼 더해준다.
                k--;                    //무적권 사용
            }
            n -= enemy[round];          //남은 병사에서 적의 수만큼 빼준다.
        }

        return round;
    }

    public static void main(String[] args) {
        int n = 7;
        int k = 3;
        int[] enemy = {4, 2, 4, 5, 3, 3, 1};
        System.out.println(solution(n, k, enemy));
    }
}
