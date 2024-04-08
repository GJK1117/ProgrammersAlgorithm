public class kjg_연속된부분수열의합 {
    public int[] solution(int[] sequence, int k) {
        // 슬라이딩 윈도우 기법 사용, [s, e] 구간을 정의, 길이를 저장할 변수 선언
        int s = 0, e = 0, len = 1000001;
        // 정답 배열 선언
        int[] answer = {s , e};
        // 구간의 합 선언, 초기값은 0위치의 값
        int sum = sequence[0];

        // e가 sequence 길이를 넘어가지 않은 한에서 반복
        while(e < sequence.length){
            // k보다 구간의 합이 작은 경우, 구간 끝 위치의 다음 수를 구간 합에 추가
            // e 인덱스를 통해 값을 가져올 때 안전하게 가져오도록(인덱스를 벗어나지 않도록) 나머지 연산을 통해 인덱스 접근
            if(sum < k) sum += sequence[++e%sequence.length];
                // k보다 구간의 합이 큰 경우, 구간의 시작 부분을 구간 합에서 제외
            else if(sum > k) sum -= sequence[s++];
                // k와 구간의 합이 같은 경우
            else{
                // 구간의 길이가 기존보다 짧을 때 answer 갱신
                if(len > e - s){
                    answer[0] = s;
                    answer[1] = e;
                    len = e - s;
                }
                // 위와 마찬가지로 안전한 인덱스 접근을 위해 나머지 연산을 사용
                sum += sequence[++e%sequence.length];
            }
        }

        // 정답 반환
        return answer;
    }
}
