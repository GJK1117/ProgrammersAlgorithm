package programmers;

import java.util.*;

public class kjh_가장큰수 {
    public static String solution(int[] numbers) {
        String answer = "";
        String[] numStr = new String[numbers.length];
        int len = numbers.length;
        for(int i=0; i<numbers.length; i++) {
            numStr[i] = String.valueOf(numbers[i]); //각 숫자를 문자열로 형변환
            //변환된 문자열을 3배해줌 ex) 34 -> 343434
            //숫자를 문자열로바꾼후 정렬할 경우(오름차순의 경우) 숫자 문자열의 맨 앞자리의 값을 기준으로 정렬됨
            //즉 "10", "2" 이렇게 올경우 숫자의 경우 2, 10 으로 정렬되겠지만 문자의 경우 앞의 문자기준으로
            //"10", "2" 이렇게 정렬됨 또한 맨 앞자리가 같을 경우 길이가 더 짧은 문자가 앞에옴
            //이 문제의 경우 각 숫자의 크기가 0~1000 이므로 각 문자열 3배를 한 후 비교해줌
            //각 숫자 문자열을 3배하는 이유는 배열 내의 숫자들을 이어 붙여서 만들 수 있는 가장 큰 숫자를 생성하기 위함임
            //ex) "3", "30", "34" 가 올 경우 34330 으로 "34", "3", "30"이 되어야 가장 큰수임
            //하지만 저 배열을 내림차순 정렬하면 "34", "30", "3"이 됨 이것을 각 3배해주면
            //"333", "303030", "343434" 이므로 앞자리가 큰것이 먼저 오게 되므로 "343434", "333", "303030" 으로 정렬됨
            numStr[i] = numStr[i] + (numStr[i]+numStr[i]);
        }
        Arrays.sort(numStr);

        for(int i=len-1; i>=0; i--){
            //["0", "0", "0" ...] 일 경우 "0"으로 예외 처리
            if(i==len-1 && Integer.parseInt(numStr[i])==0) {
                answer = "0";
                break;
            }

            //3배해줬던 문자열을 다시 3으로 나눠주고 모든 문자를 붙여서 반환
            int k = numStr[i].length()/3;
            numStr[i] = numStr[i].substring(0, k);
            answer += numStr[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};
        System.out.println(solution(numbers));
    }
}
