import java.util.*;
import java.time.*;
import java.time.format.*;

public class kjg_과제진행하기 {
    public String[] solution(String[][] plans) {
        // 결과 배열 선언
        String[] answer = new String[plans.length];
        // 스택을 통해 작업을 처리함
        Stack<String[]> s = new Stack<>();
        // 시작하는 시간을 기준으로 plans배열을 정렬
        Arrays.sort(plans, Comparator.comparing(a -> a[1]));
        // 결과 배열의 인덱스
        int idx = 0;

        // plans 배열 순회
        for (String[] plan : plans) {
            // 현재 순회중인 과제의 시작 시간 설정
            LocalTime now = LocalTime.parse(plan[1]);
            // 현재 시간을 초로 변환
            int nowSeconds = now.toSecondOfDay();

            // 스케쥴러에 있는 작업 먼저 확인
            while (!s.isEmpty()) {
                // 스케쥴러에 있는 과제의 정보를 가져옴
                String[] tmp = s.pop();
                // 현재 스케쥴러에서 가져온 과제의 시작 시간, 수행 시간을 통해 끝나는 시간을 정의
                LocalTime start = LocalTime.parse(tmp[1]);
                int playtime = Integer.parseInt(tmp[2]) * 60;
                int endSeconds = start.toSecondOfDay() + playtime;

                // 현재 시간보다 같거나 큰 경우, 처리하던 작업을 갱신하고 break
                if (nowSeconds < endSeconds) {
                    // 끝낼 예정의 시각과 현재 시각의 차이
                    int diff = endSeconds - nowSeconds;
                    // 현재 순회중인 plans 배열의 시작 시간으로 설정
                    tmp[1] = plan[1];
                    // 현재 순회중인 plans 배열의 시작 시간과 스케쥴러에 있는 작업의 끝나는 시간의 차이를 계산
                    tmp[2] = Integer.toString(diff / 60);
                    // 수정된 정보를 다시 스케쥴러에 삽입
                    s.push(tmp);
                    break;
                }

                // 현재 시간보다 작은 경우, 스케쥴러에 있는 작업을 결과 배열에 저장
                answer[idx++] = tmp[0];

                // 현재 진행한 작업 이후에 스케쥴러에 작업이 있는 경우
                if (!s.isEmpty()) {
                    // 그 과제의 시작 시간을 현재 작업을 마친 시간으로 갱신하여 저장
                    tmp = s.pop();
                    tmp[1] = LocalTime.ofSecondOfDay(endSeconds).format(DateTimeFormatter.ofPattern("HH:mm"));
                    s.push(tmp);
                }
            }

            // 현재 작업 실행
            s.push(plan);
        }

        // 스케쥴러에 남은 작업 순차적으로 처리
        while (!s.isEmpty()) {
            String[] tmp = s.pop();
            answer[idx++] = tmp[0];
        }

        // 결과 반환
        return answer;
    }
}
