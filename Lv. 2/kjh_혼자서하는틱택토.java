package programmers;

import java.util.*;

public class kjh_혼자서하는틱택토 {
    //board 에서 각 'O', 'X' 의 위치를 넣어줄 맵 -> O, X 를 키값으로 하여 각각의 문자가 있는 위치를 큐로 넣어준다.
    //일직선인지 탐색하기 위하여 Queue를 사용하여 위치값을 넣어주었다.
    static Map<Character, Queue<Location>> map = new HashMap<>();
    public static int solution(String[] board) {
        //map 초기화
        map.put('O', new LinkedList<>());
        map.put('X', new LinkedList<>());
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i].charAt(j)=='.') continue;
                //O, X 의 위치 인덱스를 큐에 넣어준다. Location 객체 생성하여 사용
                map.get(board[i].charAt(j)).offer(new Location(i, j));
            }
        }
        //O, X 의 개수를 세준다.
        int cntO = map.get('O').size();
        int cntX = map.get('X').size();
        //O가 선공이므로 X의 개수가 O의 개수보다 많으면 정상적인 상황이 아님
        //O가 X보다 2개 이상으로 많으면 X 차례에 O를 놓은 것이므로 정상적인 상황이 아님
        if(cntO<cntX || cntO-cntX>=2) return 0;
        //O와 X의 개수가 둘다 3이상이고 차이가 2이상이 아닐경우엔 일직선이 완성되었는지 확인해야한다.
        else if(cntO>=3 && cntX>=3){
            //O가 X보다 하나 많을 경우
            //ex) O X O X O X O => X가 일직선이면 게임이 끝나 O가 하나더 오지 못하므로 X가 일직선인지 검사한다.
            if(cntO>cntX) return checkSequence('X', board);
            //O와 X의 개수가 같은 경우
            //ex) O X O X O X => O가 일직선이면 게임이 끝나 X가 하나더 오지 못하므로 O가 일직선인지 검사한다.
            else return checkSequence('O', board);
        }
        else return 1;
    }

    //특정 문자(O or X)가 일직선으로 놓여져있는지 탐색하며 검사하는 메서드
    static int checkSequence(char c, String[] board){
        //상하좌우 대각선 이동하며 탐색
        int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};
        //O or X 의 위치 값이 저장된 큐를 사용하여 탐색한다.
        Queue<Location> q = map.get(c);
        while(!q.isEmpty()){
            Location l = q.poll();
            //상하좌우 대각선 모두 탐색
            for(int i=0; i<8; i++){
                int k = 0;
                int nx = l.x;
                int ny = l.y;
                //현재 위치에서 한쪽 방향으로 진행하며 검사한다.
                //이동한 위치가 board 범위를 벗어나거나 현재 탐색중인 문자가 아니라면 반복문 종료
                while(!(nx<0 || nx>=3 || ny<0 || ny>=3 || board[nx].charAt(ny)!=c)) {
                    nx += dx[i];
                    ny += dy[i];
                    ++k;
                }
                //한 방향에 3개가 일직선으로 놓여있으므로 정상적인 상황이 아님 0 리턴
                if(k==3) return 0;
            }
        }
        //한 방향에 3개가 일직선으로 놓여있지 않으면 정상적인 상황 1 리턴
        return 1;
    }

    static class Location{
        int x;  //행 인덱스
        int y;  //열 인덱스

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        String[] board = {"O.X", ".O.", "..X"};
        System.out.println(solution(board));
    }
}
