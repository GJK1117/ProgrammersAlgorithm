package programmers;

public class kjh_순위 {
    public static int solution(int n, int[][] results) {
        int answer = 0;
        int[][] win = new int[n+1][n+1];
        int[][] lose = new int[n+1][n+1];
        for(int i=0; i<results.length; i++){
            //이긴경우와 진경우 1로 초기화
            win[results[i][0]][results[i][1]]=1;
            lose[results[i][1]][results[i][0]]=1;
        }
        //플로이드 워셜 사용
        //a가 c를 이기고, c가 b를 이기면 => a는 b를 이김
        //지는 경우 동일
        for(int a=1; a<=n; a++){
            for(int b=1; b<=n; b++){
                for(int c=1; c<=n; c++){
                    //이긴 경우
                    if(win[a][c]==1 && win[c][b]==1) {
                        win[a][b] = 1;  //a는 b를 이김
                        lose[b][a] = 1; //반대로 b는 a에게 짐
                    }
                    //진 경우
                    if(lose[a][c]==1 && lose[c][b]==1) {
                        win[b][a] = 1;  //b는 a에게 이김
                        lose[a][b] = 1; //a는 b에게 짐
                    }
                }
            }
        }
        //한 선수의 이긴경우와 진경우의 합이 나머지 선수의 수(n-1)이면 그 선수의 순위는 확정
        for(int i=1; i<=n; i++){
            int res = 0;
            for(int j=1; j<=n; j++){
                if(win[i][j]==1 || lose[i][j]==1) res++;
            }
            if(res==n-1) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(solution(n, results));
    }
}
