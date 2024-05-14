import java.util.*;

class Solution {
    // 1. 시작 지점 찾기
    // 2. 시작 지점 -> 레버 위치 이동
    // 3. 레버 위치에서 도착 지점 이동
    // 4. 중간에 언제나 탈출각이 안보이면 -1 반환
    
    // 이동 정의 배열(상, 하, 좌, 우 이동)
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        // 정답 변수
        int answer = 0;
        // 시작 위치 정의
        int[] start = findStart(maps);
        // 레버 위치 정의
        int[] lever = move(start, 'L', maps);
        // 레버 위치에 도달할 수 없는 경우 -1 반환
        if(maps[lever[0]].charAt(lever[1]) != 'L'){
            return -1;
        }
        
        // 시작 위치 -> 레버 위치로 이동하는 비용 추가
        answer += lever[2];
        
        // 출구 위치 정의
        int[] exit = move(lever, 'E', maps);
        // 출구 위치에 도달할 수 없는 경우 -1 반환
        if(maps[exit[0]].charAt(exit[1]) != 'E'){
            return -1;
        }
        
        // 레버 위치 -> 출구 위치로 이동하는 비용 추가
        answer += exit[2];
        // 정답 반환
        return answer;
    }
    
    // 시작 위치를 찾는 함수
    public int[] findStart(String[] maps){
        // 문자 'S'의 위치를 찾아 반환, maps에 'S'는 반드시 존재
        for(int i = 0; i < maps.length; i++){
            for(int j = 0; j < maps[i].length(); j++){
                if(maps[i].charAt(j) == 'S'){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
    
    // 출발지 좌표 s에서 문자 e가 있는 위치까지 이동하는 함수
    public int[] move(int[] s, char e, String[] maps){
        Queue<int[]> q = new LinkedList<>();
        int r_size = maps.length, c_size = maps[0].length();
        boolean[][] visited = new boolean[r_size][c_size];
        
        q.add(new int[]{s[0], s[1], 0});
        visited[s[0]][s[1]] = true;
        while(!q.isEmpty()){
            int[] tmp = q.poll();
            if(maps[tmp[0]].charAt(tmp[1]) == e){
                return tmp;
            }
            
            for(int i = 0; i < 4; i++){
                int curr_r = tmp[0] + dr[i];
                int curr_c = tmp[1] + dc[i];
                
                if(moveCheck(curr_r, curr_c, r_size, c_size) && !visited[curr_r][curr_c]){
                    if(maps[curr_r].charAt(curr_c) != 'X'){
                        visited[curr_r][curr_c] = true;
                        q.add(new int[]{curr_r, curr_c, tmp[2] + 1});
                    }
                }
            }
        }
        // 목적지에 도달할 수 없다면 시작 위치를 반환
        return new int[]{s[0], s[1], 0};
    }
    
    // Maps를 벗어나는지 판단하는 함수
    public boolean moveCheck(int r, int c, int maps_size, int map_size){
        return (r>=0) && (r<maps_size) && (c>=0) && (c<map_size);
    }
}
