import java.util.*;

public class kjg_대충만든자판 {
    public int[] solution(String[] keymap, String[] targets) {
        // 정답 배열 선언
        int[] answer = new int[targets.length];

        // 문자 입력 최소 수를 정의할 HashMap 선언 및 Integer의 최대값으로 초기화
        Map<Character, Integer> map = new HashMap<>();
        for(char letter = 'A'; letter <= 'Z'; letter++){
            map.put(letter, Integer.MAX_VALUE);
        }

        // keymap을 통해 문자열 마다 최소 입력 수 정의, 문자가 없다면 MAX_VALUE로 저장
        for(String key : keymap){
            for(char letter = 'A'; letter <= 'Z'; letter++) {
                int idx = key.indexOf(letter);
                if(idx == -1) continue;

                int min = Math.min(idx + 1, map.get(letter));
                map.put(letter, min);
            }
        }


        // Target 문자열 읽는 반복문
        int idx = 0;
        for(String target : targets){
            // 문자열을 만들 입력 수의 합 정의
            int sum = 0;
            // 문자열을 읽어 입력 수의 합 계산
            for(int i = 0; i < target.length(); i++){
                // keymap에 없는 문자인 경우
                if(map.get(target.charAt(i))==Integer.MAX_VALUE){
                    // -1로 정의 후 break;
                    sum=-1;
                    break;
                }
                // 문자열 읽으며 계속해서 입력 수 누적
                sum += map.get(target.charAt(i));
            }
            // n번째 targets의 입력 수 저장
            answer[idx++] = sum;
        }

        // 정답 출력
        return answer;
    }
}
