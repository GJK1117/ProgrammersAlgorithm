package programmers;

public class kjh_피로도 {
    static int answer = 0;
    public static int solution(int k, int[][] dungeons) {
        for(int i=0; i<dungeons.length; i++){
            //각 던전을 도는 경우 모두 탐색
            boolean[] visited = new boolean[dungeons.length];
            dfs(i, k, 0, dungeons, visited);
        }
        return answer;
    }

    //dfs 로 모두 탐색해준다.
    static void dfs(int idx, int fatigue, int cnt, int[][] dungeons, boolean[] visited){
        //모든 던전을 돌았거나 현재 피로도가 각 던전의 최소 필요 피로도보다 작으면 던전에 입장하지 못하므로 return 해준다.
        if(visited[idx] || fatigue < dungeons[idx][0]){
            //가장 많이 돈 던전의 개수를 정답 값에 저장해준다.
            if(cnt>answer) answer = cnt;
            return;
        } else {
            //현재 피로도에서 던전의 소모 피로도만큼 빼준 후 던전의 개수를 하나 증가 시킨다.
            fatigue -= dungeons[idx][1];
            cnt++;
        }

        for(int i=0; i<dungeons.length; i++){
            //방문 처리
            visited[idx] = true;
            dfs(i, fatigue, cnt, dungeons, visited);
            //모두 리턴 후 다른 경우를 검색하려고 할때 visitied 배열을 초기화 해주어야하므로 false로 다시 설정해준다.
            visited[idx] = false;
        }
    }

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};
        System.out.println(solution(k, dungeons));
    }
}
