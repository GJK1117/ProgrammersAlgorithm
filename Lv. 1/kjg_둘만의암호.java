import java.util.*;

public class kjg_둘만의암호 {
    public String solution(String s, String skip, int index) {
        // skip에 있는 문자 저장, 처음에는 Integer로 했다가 Boolean으로 변경, 좀 더 작은 메모리 사용
        Map<Character, Boolean> map = new HashMap<>();
        // ASCII 계산을 위해 char[]로 변환, toCharArray() 사용
        char[] tmp = s.toCharArray();

        // skip에 있는 문자 저장
        for(int i = 0; i < skip.length(); i++){
            map.put(skip.charAt(i), true);
        }

        // tmp char[] 순회
        for(int i = 0; i < tmp.length; i++){
            // tmp[i]를 index만큼 문자 이동
            for(int j = 0; j < index; j++){
                // skip에 있는 문자를 건너뛰며 문자 이동
                do{
                    tmp[i] = (char)((tmp[i] - 'a' + 1) % 26 + 'a');
                }while(map.containsKey(tmp[i])); // map에 키가 있는지 확인
            }
        }

        // 결과 변수 선언 및 반환, char[]을 new String의 인자로 주면 String으로 효과적으로 변환 가능
        String answer = new String(tmp);
        return answer;
    }
}
