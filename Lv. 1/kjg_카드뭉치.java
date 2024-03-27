public class kjg_카드뭉치 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        // cards1, cards2 배열의 인덱스 정의
        int cards1_idx = 0, cards2_idx = 0;
        // 모든 비교에도 문제 없이 잘 통과한다면 결과는 Yes
        String answer = "Yes";

        // goal 배열 반복
        for(String step : goal){
            // card1에서 한 번 비교, index가 넘어가지 않도록 처리
            if(cards1_idx < cards1.length && cards1[cards1_idx].equals(step)){
                cards1_idx++;
            }
            // card2에서 한 번 비교, index가 넘어가지 않도록 처리
            else if(cards2_idx < cards2.length && cards2[cards2_idx].equals(step)){
                cards2_idx++;
            }
            // cards1, cards2 모두 없다면 결과는 No
            else{
                answer = "No";
                break;
            }
        }
        return answer;
    }1
}
