import java.util.*;

class Solution {
    // 1. 대실 시작 시간을 기준으로 시간대를 정렬, 우선순위 큐 사용
    // 2. 호텔 방은 최대 1000개까지 가능하므로 크기 1000인 배열 선언, 이 곳에는 이전 시간대의 종료 시간 + 10분이 기록되어 있음
    // 3. 시작 시간이 빠른 순부터 예약이 가능한 순서로 넣음, 이전 시간대의 종료 시간 + 10분보다 같거나 크면 예약이 가능
    public int solution(String[][] book_time) {
        // 정답 변수
        int answer = 0;
        
        // 우선순위 큐를 사용해서 대실 시작 시간대로 시간대 정렬, Integer.compare 사용하여 임의의 정렬 규칙 설정
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // book_time을 읽어 우선순위 큐에 입력
        for(String[] time : book_time){
            // replace를 사용하여 ":"을 지워 시간을 int형 변수로 유지
            int s = Integer.parseInt(time[0].replace(":", ""));
            int e = Integer.parseInt(time[1].replace(":", ""));
            pq.offer(new int[]{s, e});
        }
        
        // 호텔 방, 최대 1000개까지 가능하므로 처음 크기를 1000으로 지정
        // 이전 시간대의 종료 시간 + 10분 혹은 한 번도 예약이 안된 방이라면 0이 기록되어 있음
        int[] room = new int[1000];
        
        // 우선순위 큐를 순회
        while(!pq.isEmpty()){
            // 우선순위 큐에 입력된 정보 출력
            int[] tmp = pq.poll();
            // 0번 방부터 1000번 방까지 예약이 가능한지 확인
            for(int i = 0; i < 1001; i++){
                // 이전 예약이랑 겹치지 않는다면 예약 처리
                if(room[i] <= tmp[0]){
                    // 이전 예약이 없던 방이었다면 사용한 방 개수 증가
                    if(room[i] == 0) answer++;
                    
                    // 분을 처리할 변수, 이전 시간대의 종료 시간(분) + 10분을 저장
                    int m = tmp[1] % 100 + 10;
                    // 해당 방의 정보를 갱신, 이전 시간대의 종료 시간(분) + 10분으로 갱신
                    room[i] = tmp[1] / 100 * 100 + m / 60 * 100 + m % 60;
                    // 더 이상 이 예약을 위해 방을 탐색할 필요 없음
                    break;
                }
            }
        }
        
        // 정답 반환
        return answer;
    }
}
