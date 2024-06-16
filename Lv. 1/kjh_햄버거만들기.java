package programmers;

import java.util.*;

public class kjh_햄버거만들기 {
    public static int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for(int k : ingredient){
            stack.push(k);

            //스택의 크기가 4 미만이면 continue
            if(stack.size()<4) continue;

            //지금까지 스택에 쌓인 수가 1231(빵-야채-고기-빵)을 만족하면 pop
            if(stack.get(stack.size()-1)==1 && stack.get(stack.size()-2)==3 && stack.get(stack.size()-3)==2 && stack.get(stack.size()-4)==1){
                answer++;
                for(int i=0; i<4; i++) stack.pop();
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] ingredient = {2, 1, 1, 2, 3, 1, 2, 3, 1};
        System.out.println(solution(ingredient));
    }
}
