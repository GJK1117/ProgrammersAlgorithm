import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        // test case 6가 풀리지 않았는데, 이 점을 놓침.
        if(x==y) return 0;
        
        // 메모이제이션 방식을 사용해서 문제 풀이
        // 메모이제이션할 배열 선언
        int[] result = new int[1000001];
        // 큐를 통해 각 계산 지점을 저장, 그 지점에서 3가지 계산 방법을 적용
        Queue<int[]> q = new LinkedList<>();
        // 초기 값 입력
        q.offer(new int[]{x, 0});
        
        // 큐가 빌 때까지
        while(!q.isEmpty()){
            // 현재 계산 값과 계산 횟수 저장
            int[] tmp = q.poll();
            // 각 3가지 계산 방법의 결과를 배열로 선언
            int[] steps = {tmp[0] + n, tmp[0] * 2, tmp[0] * 3};
            
            // 반복문을 통해 3가지 계산 결과를 적용
            for(int step : steps){
                // y보단 작거나 같을 때 유효한 계산
                if(step <= y){
                    // 계산 횟수는 처음 계산 한 값이거나 이전에 계산한 횟수보다는 작아야 함
                    if(result[step] == 0 || result[step] > tmp[1] + 1){
                        // 현재에서 횟수를 하나 증가시켜 배열에 저장
                        result[step] = tmp[1] + 1;
                        // 큐에 계산 결과와 계산 횟수 입력
                        q.offer(new int[]{step, tmp[1] + 1});
                    }
                }
            }
        }
        
        // 만약 계산하고자 하는 값에 도달하지 못했을 경우 -1 반환
        if(result[y] == 0) return -1;
        // 정답 반환
        return result[y];
    }
}
