package programmers;

import java.util.*;

public class kjh_요격시스템 {
    public static int solution(int[][] targets) {
        int answer = 1;

        //람다식 사용하여 2차원배열 두번째 열 기준으로 정렬
        Arrays.sort(targets, (o1, o2) -> (o1[1]==o2[1]) ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));

        //첫번째 구간 끝값 저장
        int temp = targets[0][1];
        for(int i=1; i<targets.length; i++){
            //구간 첫번째 값이 더 크게 되면 한번에 요격할 수 있는 범위에서 벗어나므로 해당 구간 끝값 temp에 저장해 다시 시작
            if(targets[i][0] >= temp) {
                temp = targets[i][1];
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
        System.out.println(solution(targets));
    }
}
