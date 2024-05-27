package programmers;

import java.util.*;

public class kjh_연속부분수열합의개수 {
    public static int solution(int[] elements) {
        Set<Integer> answer = new HashSet<>();
        int len = elements.length;
        //구간 합 배열 정의
        for(int i=1; i<len; i++){
            answer.add(elements[i]);    //길이가 1인 연속부분 수열 값 set 에 저장
            elements[i] = elements[i]+elements[i-1];
        }
        answer.add(elements[0]);
        int start = 0, end = 1;     //start, end 포인터 정의

        while(start!=len){
            int res = 0;
            while(start!=(end%len)){
                //[7,9,1,1,4] 인 경우 구간합 배열을 구하면 [7,16,17,18,22] 이다.
                //예를 들어 인덱스 1 부터 3까지의 구간 합을 구해야 한다고 가정하면 구간합 배열 인덱스 3 위치의 값에서 0 위치의 값을 빼주면 된다.
                //start 0인 경우에는 start-1로 인해 예외가 날수 있다. 하지만 구간합 배열이 0번위치부터 누적된 합이기 때문에 이경우엔 그냥 end 위치의 값을 넣어주면된다.
                if(end<len){    //end 위치가 start보다 크고 len보다 작은 경우 계산
                    if(start==0) res = elements[end];
                    else res = elements[end]-elements[start-1];
                } else {        //end 위치가 len보다 커 start 앞으로 넘어간 경우 계산
                    //원형 수열인 경우 end인덱스가 start보다 앞에 올 수 있다 이 경우엔 아래 수식으로 계산한다.
                    //예를 들어 인덱스 3부터 1까지의 수열의 합을 구해야 한다고 가정하면 전체 구간 합에서 인덱스 0의 값을 빼준 후 end 위치의 값을 더해준다.
                    res = elements[len-1]-elements[start-1]+elements[end%len];
                }
                //구한 값을 정답 set에 넣어주고 end를 하나 증가시켜 다음 과정을 수행한다.
                answer.add(res);
                end++;
            }
            //start의 위치를 하나씩 증가시키고 end를 start 다음 인덱스로 설정한다.
            start++;
            end = start+1;
        }

        //정답 set 의 개수를 리턴해준다.
        return answer.size();
    }

    public static void main(String[] args) {
        int[] elements = {7,9,1,1,4};
        System.out.println(solution(elements));
    }
}
