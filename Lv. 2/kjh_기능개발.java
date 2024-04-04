package programmers;

import java.util.*;

public class kjh_기능개발 {
    public static List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int max = 0, cnt = -1;
        //배포 순서에 맞추어 기능을 넣을 큐
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<speeds.length; i++){
            //각 기능마다 배포까지 남은 퍼센트
            int p = 100-progresses[i];
            //각 기능마다 개발속도를 나누어 주어 배포까지 걸리는 시간(일)을 큐에 넣어줌
            //걸리는 시간이 딱 떨어지지 않을 경우 올림해준다.
            if(p%speeds[i]==0) queue.offer(p/speeds[i]);
            else queue.offer(p/speeds[i]+1);
        }
        while(!queue.isEmpty()){
            //큐를 순서대로 탐색하 하루에 배포할 수 있는 기능의 개수를 정답 리스트에 저장
            int x = queue.poll();
            if(x>max){
                max = x;
                answer.add(1);
                cnt++;
            } else {
                answer.set(cnt, answer.get(cnt)+1);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        System.out.println(solution(progresses, speeds));
    }
}
