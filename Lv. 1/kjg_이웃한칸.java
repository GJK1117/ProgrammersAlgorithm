public class kjg_이웃한칸 {
    // 상하좌우 움직임을 정의할 배열
    public int[] dh = {-1, 1, 0, 0}, dw = {0, 0, -1, 1};

    public int solution(String[][] board, int h, int w) {
        // 정답 변수
        int answer = 0;

        // 주어진 현재 위치에서 상하좌우로 이동하며 같은 색이 있는지 확인
        for(int i = 0; i < 4; i++){
            // 현재위치의 상하좌우 좌표
            int r = h + dh[i];
            int c = w + dw[i];
            // 해당 좌표가 유효한지 확인, 보드를 벗어나는 경우를 확인
            if(check(board.length, board[0].length, r, c)){
                // 같은 색이 있는 경우 정답 변수 1 증가
                if(board[h][w].equals(board[r][c])){
                    answer++;
                }
            }
        }
        // 같은 색 개수를 반환
        return answer;
    }

    // 주어진 위치가 보드를 벗어난지 확인, 유효한 경우 true, 아닌 경우 false 반환
    public boolean check(int r, int c, int h, int w){
        return (h >= 0) && (h < r) && (w >= 0) && (w < c);
    }
}
