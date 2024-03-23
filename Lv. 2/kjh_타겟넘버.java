package programmers;

import java.util.*;

public class kjh_타겟넘버 {
    static Queue<Integer> q = new LinkedList<>();
    public static int solution(int[] numbers, int target) {
        int answer = 0;
        bfs(numbers);
        //마지막 레벨에 위치한 노드의 값을 읽어 target과 같을 경우 결과값 증가
        while(!q.isEmpty()){
            if(target==q.poll()) answer++;
        }
        return answer;
    }

    static void bfs(int[] numbers) {
        //cnt: 한 레벨의 노드 개수(2,4,8 ...)
        //level: 현재 깊이
        //countPoll: 현재 레벨에서 방문한 노드 수
        int cnt = 2, level = 1, countPoll = 0;
        q.offer(-numbers[0]);
        q.offer(numbers[0]);
        while(true){
            //방문한 노드수가 현재 레벨의 노드 개수와 일치한다면 더 깊은 레벨로 이동
            if(countPoll==Math.pow(2,cnt)){
                level++;
                cnt++;
                countPoll=0;
            }
            //레벨이 최대 깊이까지 갔을 경우 break
            //맨 마지막 레벨(단말노드)에 있는 값들만 큐에 남는다.
            if(level>=numbers.length) break;

            int now = q.poll();
            //현재노드에 다음 레벨의 값을 +, - 해준 후 큐에 추가
            for(int i=0; i<2; i++){
                int k = 0;
                k = (i==0) ? now+numbers[level] : now-numbers[level];
                q.offer(k);
                countPoll++;
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {4, 1, 2, 1};
        int target = 4;
        System.out.println(solution(numbers, target));
    }
}
