import java.util.HashMap;

public class kjg_가장많이받은선물 {
    public int solution(String[] friends, String[] gifts) {
        // 정답 출력 변수
        int answer = 0;

        // 이름을 인덱스에 매핑하기 위한 해시맵 선언
        HashMap<String, Integer> index = new HashMap<>();
        // 친구들 간 오고 간 선물의 수를 기록할 2차원 배열, 인덱스는 [선물을 준 사람][선물을 받은 사람]으로 정의
        int[][] table = new int[friends.length][friends.length];
        // 선물 지수 배열
        int[] giftAmount = new int[friends.length];
        // 선물을 받은 개수 배열
        int[] giftCount = new int[friends.length];

        // 해시맵 초기화
        int idx = 0;
        for(String friend : friends){
            index.put(friend, idx++);
        }

        // 선물 받은 기록을 토대로 table, giftAmount 배열 초기화
        for(String gift : gifts){
            // 공백으로 보낸 사람, 받는 사람을 구분하여 저장
            String[] split = gift.split(" ");

            // 파싱한 문자열을 인덱스로 변환
            int from = index.get(split[0]);
            int to = index.get(split[1]);

            // [선물을 준 사람][선물을 받은 사람 인덱스] 값 증가
            table[from][to]++;

            // 선물 지수를 계산, 선물 지수는 선물을 준 수 - 선물을 받은 수 이므로 선물을 준 사람(from)
            // 는 증가하고, 선물을 받은 사람(to)는 감소
            giftAmount[from]++;
            giftAmount[to]--;
        }

        // 다음 달 선물을 받을 개수 계산, i번째 사람을 기준으로 계산
        for(int i = 0; i < friends.length; i++){
            for(int j = 0; j < friends.length; j++){
                // 서로 선물을 교환하지 않은 경우 서로 0 또는 선물을 주고 받은 개수가 같은 경우
                if(table[i][j] == table[j][i]){
                    // 선물 지수가 더 작은 사람이 j일 경우 i가 선물을 받음
                    if(giftAmount[i] > giftAmount[j]){
                        giftCount[i]++;
                    }
                    // 이 조건식에 걸리지 않는 경우, i == j인 경우 이므로 별도의 if문 처리를 하지 않았음
                }
                // i가 j보다 더 많은 선물을 받은 경우 i가 선물을 받음
                else if(table[i][j] > table[j][i]) {
                    giftCount[i]++;
                }
            }
        }

        // 각자 받은 선물 수 중 최대 값을 구함
        for(int i : giftCount){
            answer = Math.max(answer, i);
        }

        // 정답 반환
        return answer;
    }
}
