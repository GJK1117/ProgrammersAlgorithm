package programmers;

import java.util.*;

public class kjh_귤고르기 {
    public static int solution(int k, int[] tangerine) {
        int answer = 0;
        //처음 접근 했을 때는 Map을 사용하여 (크기 : 개수) 형식으로 값을 카운팅 해주었다.
        //하지만 실제로 돌려보니 메모리 초과로 런타임에러가 났다.
        //이를 해결하기 위해 배열로 변경해준 후 풀이하였다.
        int[] orange = new int[10000001];   //귤 크기당 개수를 넣어줄 배열
        //각 인덱스에 해당하는 크기의 귤 개수를 배열에 저장해준다.
        for(int i=0; i<tangerine.length; i++){
            orange[tangerine[i]]++;
        }
        //개수대로 오름차순 정렬한다.
        Arrays.sort(orange);
        //큰 값부터 차례대로 k에서 빼주어 크기별 종류의 개수를 세주어 반환한다.
        for(int i=orange.length-1; i>=0; i--){
            k -= orange[i];
            answer++;
            if(k<=0 || orange[i]==0) break;
        }
        return answer;
    }

    public static void main(String[] args) {
        int k = 6;
        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};
        System.out.println(solution(k, tangerine));
    }
}
