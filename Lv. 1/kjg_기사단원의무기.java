public class kjg_기사단원의무기 {
    public int solution(int number, int limit, int power) {
        // 정답 변수 선언, 1은 무조건 1개의 약수를 갖기 때문에 초기값 1로 설정
        int answer = 1;
        // 2부터 number까지 약수 개수 구함
        for(int i = 2; i <= number; i++){
            // 1과 자기자신은 무조건 약수이므로 초기값 2로 설정
            int cnt = 2;
            // 2부터 i의 제곱근까지 약수 개수 계산
            for(int j = 2; j * j <= i; j++){
                // 만약 i의 제곱근이 j와 같을 경우 이는 1개의 약수이므로 1증가
                if(j * j == i) cnt++;
                // 그것이 아닐 경우 모둔 약수는 짝을 이루므로 2 증가
                else if(i % j == 0) cnt+=2;
            }

            // 만약 limit를 넘어가면 power로 설정
            if(cnt > limit) cnt = power;
            // 약수 개수 합산
            answer += cnt;
        }
        // 정답 반환
        return answer;
    }
}
