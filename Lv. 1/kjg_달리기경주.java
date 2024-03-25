import java.util.*;

public class kjg_달리기경주 {
    public String[] solution(String[] players, String[] callings) {
        // 정답 배열 선언
        String[] answer = players;

        // 이름을 인덱스와 매칭하기 위한 HashMap 선언
        Map<String, Integer> order = new HashMap<>();
        // HashMap 초기화, 초기 위치로 설정
        for(int i = 0; i < players.length; i++){
            order.put(players[i], i);
        }

        // callings 배열대로 순서 변경
        for(String calling : callings){
            // 호명한 선수의 위치
            int now = order.get(calling);
            // 호명한 선수 앞에 있는 선수의 위치
            String pre = answer[now - 1];

            // 호명한 선수, 그 앞에 있는 선수 위치 변경
            answer[now] = pre;
            answer[now - 1] = calling;

            // HashMap 최신화
            order.put(calling, now - 1);
            order.put(pre, now);
        }

        // 정답 반환
        return answer;
    }
}
