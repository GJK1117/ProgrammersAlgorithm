package programmers;

import java.util.*;

public class kjh_이모티콘할인행사 {
    /**
     * 문제 풀이 방법
     * 이모티콘의 개수가 최대 7개이고 할인율도 4개로 값이 작으므로 모두 순회하며 계산해주면 된다.
     * Map 자료구조를 활용해 emoticons 배열의 인덱스를 키 값으로 하여 값에 해당 인덱스의 금액의 할인된 금액을 유지해준다.
     * Map 의 값을 표로 나타내면 아래와 같다
     * _____________ㅣ_____각 이모 티콘 별 할인 금액을 저장할 배열_____
     * emoticons[0] ㅣ { {10, emoticons[0]의 10% 할인금액}, {20%.. }, {30%.. }, {40%.. } }
     * emoticons[1] ㅣ { {10, emoticons[1]의 10% 할인금액}, {20%.. }, {30%.. }, {40%.. } }
     * emoticons[2] ㅣ { {10, emoticons[2]의 10% 할인금액}, {20%.. }, {30%.. }, {40%.. } }
     * ...
     * .....
     * 이와 같이 구성하여 각 이모티콘에 대한 할인금액이 저장된 배열에서 하나씩 골라서 각 이모티콘마다의 금액 조합 배열을 만든 후
     * 그 배열의 금액 총합을 계산하고 이모티콘 플러스 가입 여부를 구해주었다.
     */
    static int[][] user;    //users 배열을 복사해서 사용할 배열
    static int[] answer = new int[2];   //정답 배열
    static Map<Integer, int[][]> emojiBySale = new HashMap<>(); //이모티콘 별 10,20,30,40퍼 할인 금액을 넣을 맵 (이모티콘 인덱스, 할인금액 배열)
    static int len;     //emoticons 크기
    static int[][] selectedArr;     //계산할 이모티콘별 할인이 적용된 값을 저장할 배열 {{할인율, 할인된 금액}, ...}
    public static int[] solution(int[][] users, int[] emoticons) {
        user = users;
        len = emoticons.length;
        selectedArr = new int[len][2];
        for(int i=0; i<len; i++){
            int[][] arr = new int[4][2];    //emojiBySale 맵에 넣을 임시 2차원 배열 선언
            for(int j=1; j<=4; j++){
                //{할인율, 할인 금액} 형태로 저장
                arr[j-1][0] = j*10;
                arr[j-1][1] = emoticons[i]/10*(10-j);
            }
            emojiBySale.put(i, arr);        //각 이모티콘 인덱스를 키 값으로 사용
        }
        calculation(0);             //깊이 0부터 재귀함수 호출
        return answer;
    }

    static void calculation(int depth) {
        if (depth == len) {
            //각 이모티콘의 할인 금액을 모두 선택했다면 evaluate() 함수를 호출해 결과 값을 계산해준다.
            evaluate();
            return;
        }

        for (int i = 0; i < 4; i++) {
            //현재 depth 의 이모티콘의 할인율에 따른 금액중 하나씩 선택하여 재귀함수를 호출해준다.
            selectedArr[depth][0] = emojiBySale.get(depth)[i][0];   //할인율
            selectedArr[depth][1] = emojiBySale.get(depth)[i][1];   //할인 금액
            calculation(depth + 1);
        }
    }

    static void evaluate() {
        int totalSale = 0;  //전체 사용자 총 구매 비용
        int totalCount = 0; //이모티콘 플러스 가입자 수

        for (int[] u : user) {
            int userLimit = u[0];   //현재 사용자의 기준 할인율
            int userTotal = u[1];   //현재 사용자의 이모티콘 플러스 가입 기준 금액
            int userSum = 0;        //이모티콘 구매비용

            for (int i = 0; i < len; i++) {
                //할인율이 현재 사용자가 정한 할인율 이상이라면 구매한다.
                if(selectedArr[i][0] >= userLimit) userSum += selectedArr[i][1];
            }

            //이모티콘 구매 비용이 현재 사용자의 이모티콘 플러스 가입 기준 금액 이상이라면 서비스에 가입한다.
            //아니라면 전체 사용자 구매 비용에 현재 사용자가 구매한 비용을 더해준다.
            if (userSum >= userTotal) totalCount++;
            else totalSale += userSum;
        }

        //가입자 수가 가장 많은 경우에 정답 배열에 값을 넣어주고
        //가입자 수가 기존 정답 배열 값과 같다면 구매 비용이 더 높은 경우를 정답 값에 넣어준다.
        if (totalCount > answer[0] || (totalCount == answer[0] && totalSale > answer[1])) {
            answer[0] = totalCount;
            answer[1] = totalSale;
        }
    }

    public static void main(String[] args) {
        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};
        System.out.println(Arrays.toString(solution(users, emoticons)));
    }
}
