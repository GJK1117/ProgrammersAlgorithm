import java.util.*;

public class kjg_석유시추 {
    // 이동에 쓰일 배열 선언
    public int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    // 각 석유 덩어리가 갖는 블록 개수 저장할 HashMap 선언
    public Map<Integer, Integer> cnt = new HashMap<>();
    // 석유 덩어리가 갖는 블록 개수 저장할 변수 선언
    public int size = 0;

    public int solution(int[][] land) {
        // 정답 변수 선언
        int answer = 0;
        // 덩어리 개수
        int count = 0;
        // land를 순회
        for(int i = 0; i < land.length; i++){
            for(int j = 0; j < land[0].length; j++){
                // 석유를 찾으면
                if(land[i][j] == 1){
                    // 석유 블록 개수 카운트 할 변수 초기화
                    size = 0;
                    // 석유 덩어리 개수 증가
                    count++;
                    // 인접한 석유 블록을 찾고 찾은 표시를 DFS로 수행
                    DFS(i, j, count, land);
                    // 석유 덩어리가 갖는 석유 블록 개수 저장
                    cnt.put(-count, size);
                }
            }
        }

        // 지면 기준에서 땅 순회
        for(int i = 0; i < land[0].length; i++){
            // 땅 파면서 만난 석유 덩어리 저장, 중복을 방지하기 위함
            List<Integer> check = new ArrayList<>();
            // 땅 파면서 시추한 석유 블록 개수 저장
            int sum = 0;
            // 땅 파기
            for(int j = 0; j < land.length; j++){
                // 땅 팠는데 땅이 아닌데(석유인데) 시추하지 않은 석유 덩어리라면
                if(land[j][i] != 0 && !check.contains(land[j][i])){
                    // 총 석유 블록 수 증가
                    sum += cnt.get(land[j][i]);
                    // 해당 석유 덩어리의 라벨링된 수 저장
                    check.add(land[j][i]);
                }
            }
            // 한 번 시추했을 때 가장 많이 석유를 뽑은 수 저장
            answer = Math.max(answer, sum);
        }

        // 정답 반환
        return answer;
    }

    // DFS 함수, 인접한 석유 블록들을 라벨링 및 카운트
    public void DFS(int x, int y, int count, int[][] land){
        // Stack으로 DFS 구현
        Stack<int[]> s = new Stack<>();
        // 처음 위치 push
        s.push(new int[]{x, y});
        // DFS 수행
        while(!s.isEmpty()){
            // 현재 위치 반환
            int[] tmp = s.pop();
            // 현재 위치가 석유라면
            if(land[tmp[0]][tmp[1]] == 1){
                // -count로 라벨링
                land[tmp[0]][tmp[1]] = -count;
                // 석유 덩어리가 갖는 석유 블록 수 증가
                size++;
            }
            // 현재 위치와 인접한 타일 검사
            for(int i = 0; i < 4; i++){
                int r = tmp[0] + dx[i], c = tmp[1] + dy[i];
                // 유효한 위치이라면
                if(r >= 0 && r < land.length && c >= 0 && c < land[0].length){
                    // 그 위치가 석유라면 그 위치 push
                    if(land[r][c] == 1) s.push(new int[]{r, c});
                }
            }
        }
    }
}
