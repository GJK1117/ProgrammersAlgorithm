package programmers;

import java.util.*;

public class kjh_우박수열정적분 {
    public static List<Double> solution(int k, int[][] ranges) {
        List<Double> answer = new ArrayList<>();
        List<Double> area = new ArrayList<>();  //각 구간의 넓이 값 저장할 리스트
        int n = 0, x = 0, y = 0;
        area.add(0.0);  //0~0의 값은 0 이므로 0으로 초기 세팅
        while(k>1){     //k가 1이 될때까지 반복
            int tmp = k;    //계산되기 전의 k값 임시 저장
            n++;            //횟수 증가
            if(k%2==0){
                k = k/2;    //k가 짝수일 경우 2로 나누어줌
                area.add((double)tmp-((double)(tmp-k)/2));  //구간 넓이 저장
            } else {
                k = k*3+1;  //k가 홀수일 경우 3을 곱하고 1을 더해줌
                area.add((double)k-((double)(k-tmp)/2));    //구간 넓이 저장
            }
        }
        //각 구간합 계산하여 저장
        for(int i=1; i<=n; i++) area.set(i, area.get(i-1)+area.get(i));

        for(int i=0; i<ranges.length; i++){
            x = ranges[i][0];
            y = n+ranges[i][1];

            //시작점이 끝점보다 클 경우 -1
            if(x>y) answer.add(-1.0);
            //각 구간합을 저장해놨으므로 끝점 넓이에서 시작점 넓이를 빼준 값으 그 구간의 넓이 합
            else answer.add(area.get(y)-area.get(x));
        }
        return answer;
    }

    public static void main(String[] args) {
        int k = 5;
        int[][] ranges = {{0,0},{0,-1},{2,-3},{3,-3}};
        System.out.println(solution(k, ranges));
    }
}
