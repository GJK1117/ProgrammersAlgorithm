package programmers;

import java.util.Arrays;

public class kjh_카펫 {
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int n = 1;  //yellow 영역의 세로길이
        while(true){
            //yello가 나누어 떨어지지 않으면 사각형으로 색칠을 못하므로 조건을 걸어준다.
            if(yellow%n==0){
                //yellow 영역의 가로 길이를 계산
                int a = yellow/n;
                //brown 한줄의 테두리이므로 yellow의 가로, 세로 길이을 이용해 brown 개수 구함
                int b = (a+2)*2+(n*2);
                if(brown == b) {
                    answer[0] = a+2;    //가로 길이
                    answer[1] = n+2;    //세로 길이
                    break;
                }
            }
            n++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int brown = 10;
        int yellow = 2;
        System.out.println(Arrays.toString(solution(brown, yellow)));
    }
}
