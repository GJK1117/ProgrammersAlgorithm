package programmers;

import java.util.*;

public class kjh_택배상자 {
    public static int solution(int[] order) {
        int idx = 0;
        Queue<Integer> mainBelt = new LinkedList<>();   //메인 컨테이너 벨트
        Stack<Integer> subBelt = new Stack<>();         //보조 컨테이너 벨트
        for(int i=1; i<=order.length; i++) mainBelt.offer(i);   //메인 컨테이너 벨트에 숫자를 순서대로 넣어준다.

        while(!mainBelt.isEmpty()){
            int box = mainBelt.poll();
            if(box!=order[idx]) {           //메인 컨테이너의 현재 택배상자가 현재 order 의 값과 다를 경우
                if(!subBelt.isEmpty()){
                    //보조 컨테이너 벨트의 첫번째 상자가 현재 order 의 값과 같을 경우 해당 상자를 실어주고 다음 order 로 넘어간다.
                    if(subBelt.peek()==order[idx]){
                        subBelt.pop();
                        idx++;
                    }
                }
                //보조 컨테이너 벨트의 첫번째 상자가 현재 order 의 값과 다를 경우 보조 컨테이너 벨트에 상자를 넣어준다.
                subBelt.push(box);
                continue;
            }
            idx++;  //다음 order 로 넘어간다.

            //보조 컨테이너 벨트에 있는 상자를 검사해준다.
            while(!subBelt.isEmpty() && subBelt.peek()==order[idx]){
                int b = subBelt.pop();
                idx++;
            }
        }

        return idx;
    }

    public static void main(String[] args) {
        int[] order = {4, 3, 1, 2, 5};
        System.out.println(solution(order));
    }
}
