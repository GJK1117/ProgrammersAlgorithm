import java.util.*;

public class kjg_개인정보수집유효기간 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 오늘 날자를 int형으로 변환, replace를 통해 "."을 제거
        int date = Integer.parseInt(today.replace(".", ""));

        // 약관의 기간을 저장해둘 HashMap 선언
        Map<String, Integer> map = new HashMap<>();
        // 결과를 저장할 LinkedList 선언, LinkedList를 사용한 이유는 결과의 개수를 모르기 때문
        List<Integer> result = new LinkedList<>();

        // 약관의 기간을 HashMap에 저장
        for(String term : terms){
            String[] tmp = term.split(" ");
            map.put(tmp[0], Integer.parseInt(tmp[1]));
        }

        // privacies를 순회하며 유효기간을 계산
        int idx = 1;
        for(String privacy : privacies){
            // privacy를 " " 단위로 나눔, 개인정보 수집일자와, 약관 종류로 나누어 저장
            String[] tmp = privacy.split(" ");
            // 개인정보 수집일자를 int형으로 변환, replace를 통해 "."을 제거
            int pri_date = Integer.parseInt(tmp[0].replace(".", ""));
            // HashMap에서 약관 기간을 가져옴
            int term = map.get(tmp[1]);

            // 개인정보 수집일자의 월 정보를 파싱
            int month = (pri_date/100)%100;

            // 약관의 기간을 더했을 때, 월 최대 표기 수인 12을 넘는 경우
            if(month + term > 12){
                // 기존 월 정보를 제거
                pri_date -= month * 100;
                // 약관 기간만큼 월 정보를 더함
                month += term;

                // 월 정보가 12를 넘는 경우 12로 나누어 계산
                while(month > 12){
                    // 월 수 12마다 1년을 추가
                    pri_date += 10000;
                    month -= 12;
                }
                // 남은 월수를 계산
                pri_date += month * 100;
            }
            // 월 최대 표기 수인 12을 넘지 않는 경우 그대로 계산
            else pri_date += term * 100;

            // 개인정보 수집일자 + 약관기간이 오늘 날짜이거나 이전인 경우
            if(pri_date <= date) result.add(idx);
            idx++;
        }

        // LinkedList를 int[]로 변환
        int[] answer = new int[result.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = result.get(i);
        }
        // 결과 출력
        return answer;
    }
}
