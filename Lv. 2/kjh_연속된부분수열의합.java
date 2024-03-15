package programmers;

import java.util.Arrays;

public class kjh_연속된부분수열의합 {
    public static int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        //투 포인터 사용
        int start = 0, end = 0, sum = sequence[end], len = Integer.MAX_VALUE;
        while(true){
            //포인터 사이 구간의 합이 k와 동일할경우 구간이 가장 짧은지 확인
            if(sum==k){
                if(len>end-start) {
                    len = end-start;
                    answer[0] = start;
                    answer[1] = end;
                }
                //두 포인터가 같은 위치에 있는지 확인
                //같은 위치에 있는경우 두 포인터 모두 하나 증가시켜서 진행
                if (end-start>0) sum-=sequence[start++];
                else {
                    start +=1;
                    end += 1;
                    if(end>=sequence.length) break;
                    sum = sequence[end];
                }
            } else if(sum>k){   //포인터 사이 구간의 합이 k보다 클 경우 값을 감소시키기 위해 start 인덱스 증가
                sum -= sequence[start];
                start++;
                if(start>=sequence.length) break;
            } else {    //포인터 사이 구간의 합이 k보다 작을 경우 값을 증가시키기 위해 end 인덱스 증가
                end++;
                if(end>=sequence.length) break;
                sum += sequence[end];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] sequence = {1, 2, 3, 4, 5};
        int k = 7;
        System.out.println(Arrays.toString(solution(sequence, k)));
    }
}
