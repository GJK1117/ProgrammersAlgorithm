package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class kjh_달리기경주 {
    public static String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        Map<String, Integer> map1 = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();
        for(int i=0; i<players.length; i++){
            map1.put(players[i], i);
            map2.put(i, players[i]);
        }
        for(int i=0; i<callings.length; i++){
            int v1 = map1.get(callings[i]);
            String v2 = map2.get(v1-1);

            map1.replace(callings[i], v1, v1-1);
            map1.replace(v2, v1-1, v1);
            map2.replace(v1-1, v2, callings[i]);
            map2.replace(v1, callings[i], v2);
        }
        for(int i=0; i<players.length; i++){
            answer[i] = map2.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine"};
        System.out.println(Arrays.toString(solution(players, callings)));
    }
}