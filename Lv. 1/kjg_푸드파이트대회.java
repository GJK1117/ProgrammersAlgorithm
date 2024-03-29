public class kjg_푸드파이트대회 {
    public String solution(int[] food) {
        // StringBuilder로 문자열 연산을 효율적으로 수행
        // 왼쪽, 오른쪽 시작 문자열 선언
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();

        // food 배열 순회
        for(int i = 1; i < food.length; i++){
            // food[i]가 홀수이면 -1하여 짝수로 조정 양쪽의 배치모양이 같아야하기 때문
            if(food[i] % 2 == 1) food[i]--;

            // food[i] / 2만큼 양쪽에 i를 추가
            for(int j = 0; j < food[i] / 2; j++){
                left.append(i);
                right.append(i);
            }
        }

        // 오른쪽은 뒤집어서 추가
        right.reverse();
        String answer = left + "0" + right;

        // 정답 반환
        return answer;
    }
}
