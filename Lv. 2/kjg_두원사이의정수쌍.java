public class kjg_두원사이의정수쌍 {
    // 힌트 참고함, 원의 방정식 사용
    public long solution(int r1, int r2) {
        // 정답 변수 선언
        long answer = 0;
        // X축은 포함하되, Y축을 포함하지 않고 1사분면의 점을 구함
        // x 좌표 값을 순회
        for(long i = 0; i < r2; i++){
            // 작은원, 큰원의 y위치를 구함
            double y1 = Math.sqrt((long)r1*r1 - (long)i*i);
            double y2 = Math.sqrt((long)r2*r2 - (long)i*i);

            // x가 r1보다 작거나 같은 경우
            if(i >= r1){
                // 1~큰 원의 y값 내림한 수
                answer += (long)Math.floor(y2);
            }
            // x가 r1보다 큰 경우
            else{
                // 작은 원의 y값 올림한 수 ~ 큰 원의 y값 내림한 수
                answer += ((long)Math.floor(y2) - (long)Math.ceil(y1) + 1);
            }
        }

        // 4개의 사분면이 있으므로 4배를 함
        answer*=4;
        // 정답 변수 선언
        return answer;
    }
}
