package programmers;

import java.util.*;

public class kjh_할인행사 {
    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int start=0, end=9; //투포인터 선언
        Map<String,Integer> discountMap = new HashMap<>();  //할인되는 품목
        Map<String,Integer> buyMap = new HashMap<>();       //내가 살 품목
        for(int i=0; i<want.length; i++) buyMap.put(want[i], number[i]);

        //처음 10일 동안 할인되는 품목 초기화
        for(int i=0; i<10; i++){
            if(!discountMap.containsKey(discount[i])){
                discountMap.put(discount[i], 0);
            }
            discountMap.put(discount[i], discountMap.get(discount[i])+1);
        }

        while(true){
            boolean possible = true;    //회원가입 할지 말지 결정하는 변수
            for(String s : buyMap.keySet()){
                //할인 품목에 내가 살 품목이 없는 경우 break
                if(!discountMap.containsKey(s)) {
                    possible = false;
                    break;
                }
                else {
                    //할인 품목의 수량이 내가 살 품목의 수량보다 작은 경우 break
                    if(buyMap.get(s)>discountMap.get(s)) {
                        possible = false;
                        break;
                    }
                }
            }
            //회원가입 할 수 있느 경우 answer 증가
            if(possible) answer++;

            //end 포인터가 discount 배열 넘어갈 경우 break
            if(end==discount.length-1) break;

            //start 포인터 1 증가 시킴
            //기존 start 포인터에 위치하던 품목은 할인되는 품목에서 빼준다.
            int d = discountMap.get(discount[start]);
            discountMap.put(discount[start++], d-1);

            //end 포인터 1 증가 시킴
            //기존 할인되는 품목에 end 포인터가 가리키는 할인품목을 추가해준다.
            if(!discountMap.containsKey(discount[++end])){
                discountMap.put(discount[end], 1);
            } else {
                discountMap.put(discount[end], discountMap.get(discount[end])+1);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3,2,2,2,1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        System.out.println(solution(want, number, discount));
    }
}
