public class kjg_붕대감기 {

    public int solution(int[] bandage, int health, int[][] attacks) {
        // 결과 값을 저장할 변수, 초기값은 최대 체력 값
        int answer = health;
        // 처음 시간 정의, 1초부터 시작
        int sec = 1;
        // 최대 체력 값 저장
        int max_health = health;
        // 공격 받은 기록을 읽음, 시간순서로 저장되어 있다는 가정하에 순차적으로 읽음
        for(int[] attack : attacks){
            // 공격 받은 시간 저장
            int now = attack[0];
            // 이전 시간부터 체력 회복 계산
            answer = Math.min(max_health, answer+health_cal(bandage, now - sec));
            // 공격받은 시각 1초 뒤부터 다음 연산 시작
            sec = now + 1;

            // 몬스터에게 피해받은 피해량 계산
            answer -= attack[1];
            // 체력이 0이하가 되는 경우 캐릭터 사망, -1 반환
            if(answer <= 0){
                return -1;
            }
        }
        return answer;
    }

    // 공격받은 시간 1초전까지 회복된 체력을 계산하는 함수
    public int health_cal(int[] bandage, int t){
        // 이전에 초당 회복된 체력 량
        int healbysec = t * bandage[1];
        // 이전에 추가적인 체력 회복 량
        int bonusheal = (t / bandage[0]) * bandage[2];
        // 전체적인 회복량 반환
        return healbysec + bonusheal;
    }
}
