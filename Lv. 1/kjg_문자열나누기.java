public class kjg_문자열나누기 {
    public int solution(String s) {
        // 정답 변수 선언
        int answer = 0;

        // s 문자열을 순회
        for(int i = 0; i < s.length(); i++){
            // i번째 문자 저장
            char tmp = s.charAt(i);
            // i번쨰 문자와 같은 또는 다른 문자 수 세는 변수 선언
            // 자기자신이 포함되어 x_cnt는 1로 시작
            int x_cnt = 1, Nonx_cnt = 0;

            // i번째 문자와 같은 문자, 다른 문자 수 계산
            for(int j = i + 1; j < s.length(); j++){
                // 같은 경우 x_cnt 증가
                if(tmp == s.charAt(j)) x_cnt++;
                // 아닌 경우 Nonx_cnt 증가
                else Nonx_cnt++;
                // 계산 중간마다 두 변수가 같아지는 지 확인, 같다면 문자열 나눌 수 있음
                if(x_cnt == Nonx_cnt){
                    // 정답 변수 증가
                    answer++;
                    // i번째 위치 최신화
                    i = j;
                    break;
                }
            }

            // 문자열 전체를 읽을 동안 같지 않았을 경우와 마지막 부분 문자열이 너무 짧은 경우
            if(x_cnt != Nonx_cnt){
                answer++;
                break;
            }
        }

        // 정답 반환
        return answer;
    }
}
