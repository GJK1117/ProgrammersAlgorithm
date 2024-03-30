package programmers;

import java.util.*;

public class kjh_전화번호목록 {
    public static boolean solution(String[] phone_book) {
        //문자열 배열 정렬
        //숫자 문자열의 경우 사전순으로 정렬된다.
        //{"1", "3", "123", "55", "20"} -정렬-> {"1", "123", "20", "3", "55"}
        //따라서 이 문제의 경우 정렬을 한뒤 현재 문자열이 바로 뒤의 문자열에 포함되는지만 확인하면 된다.
        Arrays.sort(phone_book);
        for(int i=0; i<phone_book.length-1; i++){
            if(phone_book[i+1].length() < phone_book[i].length()) continue;
            //그냥 contains를 해주면 접두사가 아닌 아무데나 포함되도 false를 반환하므로 substring을 활용해 접두사만 비교해준다.
            if(phone_book[i+1].substring(0, phone_book[i].length()).contains(phone_book[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};
        System.out.println(solution(phone_book));
    }
}
