package programmers;

import java.util.*;

public class kjh_의상 {
    public static int solution(String[][] clothes) {
        //의상 종류마다 의상의 개수를 넣을 키 (의상종류 : 의상개수)
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<clothes.length; i++){
            //의상종류가 맵에 존재하지 않는다면 0으로 초기화
            if(!map.containsKey(clothes[i][1])) map.put(clothes[i][1], 0);
            //의상 종류마다의 의상 개수 카운팅
            Integer k = map.get(clothes[i][1])+1;
            map.put(clothes[i][1], k);
        }
        int[] c = new int[map.size()];
        int cnt = 0;
        //int 배열에 각 의상종류마다의 개수를 넣어준다.
        for(String cloth : map.keySet()) c[cnt++] = map.get(cloth);
        int answer = c[0];
        //개수를 구해주면되는데 각 의상 종류마다의 모든 조합을 구해주어야함.
        //예를 들어 종류가 4개 a, b, c, d 가 있다고 가정하면
        //가능한 서로다른 조합의 수 = a+b+c+d+ab+ac+ad+bc+bd+cd+abc+abd+acd+bcd+abcd
        //이것을 정리하면 => a+(a+1)(b+(b+1)(c+(c+1)d) 이와 같은 식이 나온다.
        //이 식의 뒤에서부터 계산해 나가면 가능한 서로다른 조합의 개수를 구할 수 있다.
        for(int i=1; i<c.length; i++) answer = c[i]+(c[i]+1)*answer;
        return answer;
    }

    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"},{"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(solution(clothes));
    }
}
