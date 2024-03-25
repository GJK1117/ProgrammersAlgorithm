public class kjg_덧칠하기 {
    public int solution(int n, int m, int[] section) {
        // 정답 변수 선언
        int answer = 0;
        // 롤러의 시작위치 끝 위치 선언
        int s = -1, e = 0;

        // 칠해야하는 벽면 탐색
        for(int wall : section){
            // 롤러의 첫 시작이거나, 이전 롤러의 범위를 벗어난 경우
            if(s==-1 || e < wall){
                // 롤러의 위치 정의, n이 넘어가는 것으로 계산해도 잘 나와서 예외처리는 하지 않았음
                s = wall;
                e = wall+m-1;
                answer++;
            }
        }

        // 정답 반환
        return answer;
    }
}
