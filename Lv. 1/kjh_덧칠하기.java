package programmers;

import java.util.*;

public class kjh_덧칠하기 {
    public static int solution(int n, int m, int[] section) {
        int answer = 1, sum = 0;
        for(int i=0; i<section.length-1; i++){
            sum -= (section[i]-section[i+1]);
            if(sum > m-1){
                answer++;
                sum = 0;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 8;
        int m = 4;
        int[] section = {2, 3, 6};
        System.out.println(solution(n, m, section));
    }
}
