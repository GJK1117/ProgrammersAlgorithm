package programmers;

import java.util.*;

public class kjh_롤케이크자르기 {
    public static int solution(int[] topping) {
        int answer = 0;
        int idx = 1;
        Map<Integer, Integer> chulsu = new HashMap<>();     //철수의 케이크 (종류, 개수)
        Map<Integer, Integer> brother = new HashMap<>();    //동생의 케이크 (종류, 개수)

        //초기 세팅, 철수 1개 동생 나머지로 나눔
        for(int i=0; i<topping.length; i++){
            if(i==0) chulsu.put(topping[i], 1);
            else {
                if(!brother.containsKey(topping[i])){
                    brother.put(topping[i], 1);
                } else {
                    brother.put(topping[i], brother.get(topping[i])+1);
                }
            }
        }

        //인덱스를 증가시키면서 롤케이크를 나누는 모든 경우를 판단해준다.
        while(idx<topping.length){
            int now = topping[idx]; //현재 토핑

            //현재 토핑을 chulsu에게 추가
            if(!chulsu.containsKey(now)) chulsu.put(now, 1);
            else chulsu.put(now, chulsu.get(now)+1);

            //반대로 현재 토핑을 동생이 가지고 있는 토핑에서 빼줌
            int k = brother.get(now)-1;
            if(k==0) brother.remove(now);
            else brother.put(now, k);

            //두사람이 가지고 있는 종류가 같으면 answer 증가
            if(chulsu.size()==brother.size()) answer++;

            idx++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};
        System.out.println(solution(topping));
    }
}
