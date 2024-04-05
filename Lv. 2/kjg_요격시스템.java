import java.util.*;

public class kjg_요격시스템 {
    // 처음 생각 방식에 대한 힌트 참고함
    public int solution(int[][] targets) {
        int answer = 0;
        // target[x]의 두번째 요소를 기준으로 오름차순 정렬
        // Comparator 정의를 통해 두번째 요소를 기준으로 정렬하도록 정의
        Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));

        // e 시간이 작은 미사일부터 계산
        for(int i = 0; i < targets.length; i++){
            // 해당 미사일의 끝나는 시간 저장
            int end = targets[i][1];
            // 현재 다음 것부터 요격 미사일로 같이 처리가 가능한지 계산
            for(int j = i + 1; j < targets.length; j++){
                // i번째 폭격 미사일의 e 시간보다 j번째 폭격 미사일의 s 시간이 같거나 늦은 경우
                // 같은 요격 미사일로 처리 불가능
                if(end <= targets[j][0]) {
                    // 외부 반복문의 증감을 계산하여 j위치부터 계산하도록 처리
                    i = j - 1;
                    break;
                }
                // 다음 요격미사일 위치를 계속해서 갱신
                i = j;
            }
            // 요격 미사일 수 증가
            answer++;
        }

        // 정답 반환
        return answer;
    }
}
