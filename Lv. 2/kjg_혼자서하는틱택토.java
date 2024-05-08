import java.util.*;

class Solution {
    // 나올 수 없는 상황에 초점을 둬서 문제 풀이
    public int solution(String[] board) {
        int O_cnt = 0, X_cnt = 0;
        
        // O, X 개수 세기
        for(String row : board){
            for(int i = 0; i < row.length(); i++){
                if(row.charAt(i) == 'O') O_cnt++;
                if(row.charAt(i) == 'X') X_cnt++;

            }
        }
        
        // 1. X개수는 O개수를 넘어설 수 없음
        if(X_cnt > O_cnt) return 0;
        
        // 2. O, X 개수의 차이는 2개이상 날 수 없음
        if(Math.abs(X_cnt - O_cnt) >= 2) return 0;
        
        // 3. O 한줄이 완성됐는데, X의 개수가 O개수와 같아질 수 없음
        // 4. X 한줄이 완성됐는데, O개수가 X개수를 넘어가서는 안됨
        // 3,4 번 조건을 가로, 세로, 대각선에서 검사
        
        if(horizontalCheck(board, X_cnt, O_cnt)) return 0;
        
        if(verticalCheck(board, X_cnt, O_cnt)) return 0;
        
        if(crossCheck(board, X_cnt, O_cnt)) return 0;
        
        return 1;
    }
    
    public boolean verticalCheck(String[] board, int X_cnt, int O_cnt){
        for(int i = 0; i < board[0].length(); i++){
            if(board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)){
                if(board[0].charAt(i) == 'O' && X_cnt == O_cnt){
                    return true;
                }    
                if(board[0].charAt(i) == 'X' && X_cnt < O_cnt){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean horizontalCheck(String[] board, int X_cnt, int O_cnt){
        for(String row : board){
            if (row.equals("OOO") && X_cnt == O_cnt){
                return true;
            }
            
            if (row.equals("XXX") && X_cnt < O_cnt){
                return true;
            }
        }
        return false;
    }
    
    public boolean crossCheck(String[] board, int X_cnt, int O_cnt){
        if(board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)){
            if(board[1].charAt(1) == 'O' && X_cnt == O_cnt) return true;
            if(board[1].charAt(1) == 'X' && X_cnt < O_cnt) return true;
        }
        
        if(board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)){
            if(board[1].charAt(1) == 'O' && X_cnt == O_cnt) return true;
            if(board[1].charAt(1) == 'X' && X_cnt < O_cnt) return true;
        }
        return false;
    }
}
