import java.util.*;

class Solution {
    // 1. 순차적으로 0,0부터 섬 탐색
    // 2. 섬을 발견하면 이어진 땅을 전부 탐색하여 리스트에 추가
    // 3. 리스트에 들어간 값을 정렬 후 배열로 반환
    
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        List<Integer> days = new ArrayList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        
        for(int i = 0; i < maps.length; i++){
            for(int j = 0; j < maps[0].length(); j++){
                char now = maps[i].charAt(j);
                if(now == 'X') continue;
                if(!visited[i][j]) {
                    days.add(dfs(maps, visited, i, j));
                }
            }
        }
        
        if(days.size() == 0) return new int[]{-1};
        Collections.sort(days);
      // stream API를 사용한 list -> int[] 변환
        return days.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int dfs(String[] maps, boolean[][] visited, int r, int c){
        int result = Character.getNumericValue(maps[r].charAt(c));
        Stack<int[]> s = new Stack<>();
        s.push(new int[]{r, c});
        visited[r][c] = true;
        int r_size = maps.length, c_size = maps[0].length();
        
        while(!s.isEmpty()){
            int[] tmp = s.pop();
            for(int i = 0; i < 4; i++){
                int now_r = tmp[0] + dr[i];
                int now_c = tmp[1] + dc[i];
                if(isValid(now_r, now_c, r_size, c_size) && !visited[now_r][now_c]){
                    if(maps[now_r].charAt(now_c) == 'X') continue;
                    result += Character.getNumericValue(maps[now_r].charAt(now_c));
                    visited[now_r][now_c] = true;
                    s.push(new int[]{now_r, now_c});
                }
            }
        }
        
        return result;
    }
    
    public boolean isValid(int r, int c, int r_size, int c_size){
        return (r >= 0) && (r < r_size) && (c >= 0) && (c < c_size);
    }
}
