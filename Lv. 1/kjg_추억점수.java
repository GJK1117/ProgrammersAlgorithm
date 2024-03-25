import java.util.*;

public class kjg_추억점수 {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        // 정답 변수 선언
        int[] answer = new int[photo.length];
        // 추억 점수를 이름과 매칭하기 위한 HashMap 선언
        Map<String, Integer> map = new HashMap<>();

        // HashMap 초기화
        for(int i = 0; i < name.length; i++){
            map.put(name[i], yearning[i]);
        }

        // photo 배열을 순회하며 추억 점수 계산
        int idx = 0;
        for(String[] members : photo){
            for(String member : members){
                if(map.get(member) != null){
                    answer[idx] += map.get(member);
                }
            }
            idx++;
        }

        // 결과 반환
        return answer;
    }
}
